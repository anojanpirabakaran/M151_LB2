package com.example.webshop_be.domain.paymentdetail;

import com.example.webshop_be.config.error.BadRequestException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService {

    private final PaymentDetailRepository repository;


    private static final String NO_SUCH_ELEMENT_ERROR_MSG =
            "Entity with ID '%s' could not be found";

    @Autowired
    public PaymentDetailServiceImpl(PaymentDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaymentDetail findById(String id) {
        Optional<PaymentDetail> paymentDetail = repository.findById(id);

        if (paymentDetail.isPresent()) {
            return paymentDetail.get();
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public PaymentDetail createPayment(PaymentDetail paymentDetail) {
        return repository.save(paymentDetail);
    }

    @Override
    public String updatePayment(String id, PaymentDetail paymentDetail) {
        if (repository.existsById(id)) {
            checkUpdatedEntityId(id, paymentDetail);

            paymentDetail.setId(id);
            repository.save(paymentDetail);
            return "Payment saved";
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public void deleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException(String.format(NO_SUCH_ELEMENT_ERROR_MSG, id));
        }
    }

    @Override
    public List<PaymentDetail> getAllPayments() {
        return repository.findAll();
    }

    protected void checkUpdatedEntityId(String id, PaymentDetail paymentDetail) {
        if (paymentDetail.getId() != null) {
            if (id.equals(paymentDetail.getId())) {
                return;
            }
            throw new BadRequestException(
                    String.format("Path variable ID '%s' and Request body ID '%s' are not equal",
                            id, paymentDetail.getId()));
        }
    }
}
