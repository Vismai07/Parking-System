package com.parking_sys.park.controller.dto;

import java.time.LocalDateTime;

public class CheckoutResponse {

    public Long transactionId;
    public LocalDateTime checkinTime;
    public LocalDateTime checkoutTime;
    public double amount;
    public String spotLabel;

    public CheckoutResponse(Long transactionId,
                            LocalDateTime checkinTime,
                            LocalDateTime checkoutTime,
                            double amount,
                            String spotLabel) {
        this.transactionId = transactionId;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.amount = amount;
        this.spotLabel = spotLabel;
    }
}
