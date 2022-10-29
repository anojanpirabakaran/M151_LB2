package com.example.webshop_be.domain.paymentdetail;

import com.example.webshop_be.config.generic.ExtendedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "payment_detail")
public class PaymentDetail extends ExtendedEntity {

    @Column(name = "card_number")
    @Size(min = 1, max = 16)
    private String cardNumber;

    @Column(name = "expired_year")
    @Size(min = 1, max = 4)
    private int expiredYear;

    @Column(name = "cvv")
    @Size(min = 1, max = 3)
    private int cvv;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpiredYear() {
        return expiredYear;
    }

    public void setExpiredYear(int expiredYear) {
        this.expiredYear = expiredYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
