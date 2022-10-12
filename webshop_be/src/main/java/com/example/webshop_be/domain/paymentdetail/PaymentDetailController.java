package com.example.webshop_be.domain.paymentdetail;

import com.example.webshop_be.domain.paymentdetail.mapper.PaymentDetailMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-details")
public class PaymentDetailController {

    private final PaymentDetailMapper mapper;

    private final PaymentDetailService paymentDetailService;

    @Autowired
    public PaymentDetailController(PaymentDetailMapper mapper,
                                   PaymentDetailService paymentDetailService) {
        this.mapper = mapper;
        this.paymentDetailService = paymentDetailService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<PaymentDetailDTO>> getAll() {
        List<PaymentDetail> paymentDetails = paymentDetailService.getAllPayments();
        return new ResponseEntity<>(mapper.toDTOs(paymentDetails), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<PaymentDetailDTO> getById(@PathVariable String id) {
        PaymentDetail paymentDetail = paymentDetailService.findById(id);
        return new ResponseEntity<>(mapper.toDTO(paymentDetail), HttpStatus.OK);
    }

    //TODO: Create, Update, Delete
}
