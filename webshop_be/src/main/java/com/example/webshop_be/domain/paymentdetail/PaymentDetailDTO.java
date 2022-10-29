package com.example.webshop_be.domain.paymentdetail;

import com.example.webshop_be.config.generic.ExtendedDTO;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PaymentDetailDTO extends ExtendedDTO {

    @NotNull
    @Size(min = 1, max = 16)
    protected String cardNumber;

    @NotNull
    @Size(min = 1, max = 4)
    protected int expiredYear;

    @NotNull
    @Size(min = 1, max = 3)
    protected int cvv;

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
