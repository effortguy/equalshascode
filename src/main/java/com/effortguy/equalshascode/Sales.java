package com.effortguy.equalshascode;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class Sales {

    private Long id;
    private Long ownerId;
    private LocalDate payDate;
    private String calculateCode;
    private int totalAmount;
    private int mobileAmount;
    private int creditCardAmount;
    private int cashAmount;

    @Builder
    public Sales(Long id, Long ownerId, LocalDate payDate, String calculateCode, int totalAmount, int mobileAmount, int creditCardAmount, int cashAmount) {
        this.id = id;
        this.ownerId = ownerId;
        this.payDate = payDate;
        this.calculateCode = calculateCode;
        this.totalAmount = totalAmount;
        this.mobileAmount = mobileAmount;
        this.creditCardAmount = creditCardAmount;
        this.cashAmount = cashAmount;
    }

    public void add(int amount, Payment.Method paymentMethod) {
        if(paymentMethod == Payment.Method.MOBILE) { mobileAmount = amount; }
        else if(paymentMethod == Payment.Method.CREDIT_CARD) { creditCardAmount = amount; }
        else if(paymentMethod == Payment.Method.CASH) { cashAmount = amount; }
    }
}
