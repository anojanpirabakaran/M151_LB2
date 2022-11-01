package com.example.webshop_be.domain.paymentdetail;

import java.util.List;

public interface PaymentDetailService {

    PaymentDetail findById(String id);

    PaymentDetail createPayment(PaymentDetail paymentDetail);

    String updatePayment(String id, PaymentDetail paymentDetail) throws Exception;

    void deleteById(String id);

    List<PaymentDetail> getAllPayments();
}
