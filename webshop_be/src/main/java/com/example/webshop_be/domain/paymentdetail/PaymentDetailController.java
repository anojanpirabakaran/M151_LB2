package com.example.webshop_be.domain.paymentdetail;

import com.example.webshop_be.domain.paymentdetail.mapper.PaymentDetailMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping({"/", ""})
    public ResponseEntity<PaymentDetailDTO> create(@RequestBody PaymentDetailDTO paymentDetailDTO)
            throws Exception {
        PaymentDetail paymentDetail =
                paymentDetailService.createPayment(mapper.fromDTO(paymentDetailDTO));
        return new ResponseEntity<>(mapper.toDTO(paymentDetail), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<PaymentDetailDTO> updateById(@PathVariable String id,
                                                       @RequestBody
                                                       PaymentDetailDTO paymentDetailDTO)
            throws Exception {
        PaymentDetail paymentDetail = mapper.fromDTO(paymentDetailDTO);
        paymentDetailService.updatePayment(id, paymentDetail);
        return new ResponseEntity<>(mapper.toDTO(paymentDetail), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        paymentDetailService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
