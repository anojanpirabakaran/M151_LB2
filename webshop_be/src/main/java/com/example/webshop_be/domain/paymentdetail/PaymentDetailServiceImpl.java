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

    private static final String SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG =
            "Entity with ID '%s' already exists";

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
        if (repository.existsById(paymentDetail.getId())) {
            throw new BadRequestException(
                    String.format(SUCH_ELEMENT_ALREADY_EXISTS_ERROR_MSG, paymentDetail.getId()));
        } else {
            return repository.save(paymentDetail);
        }
    }

    @Override
    public String updatePayment(String id, PaymentDetail paymentDetail) {
        repository.findById(id)
                .map(paymentDetail1 -> {
                    paymentDetail1.setExpiredYear(paymentDetail.getExpiredYear());
                    paymentDetail1.setCvv(paymentDetail.getCvv());
                    paymentDetail1.setCardNumber(paymentDetail.getCardNumber());
                    repository.save(paymentDetail1);
                    return "Payment got updated";
                }).orElseGet(() -> {
                    paymentDetail.setId(id);
                    repository.save(paymentDetail);
                    return "Payment got inserted";
                });
        return "Payment is updated";
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
        if (repository.findAll().isEmpty()) {
            throw new NoSuchElementException(String.format("No User found in the database"));
        }
        return repository.findAll();
    }
}
