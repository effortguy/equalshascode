package com.effortguy.equalshascode;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class Payment {

    private Long id;
    private Long ownerId;
    private LocalDate payDate;
    private String calculateCode;
    private Method method;
    private int price;

    @Builder
    public Payment(Long id, Long ownerId, LocalDate payDate, String calculateCode, Method method, int price) {
        this.id = id;
        this.ownerId = ownerId;
        this.payDate = payDate;
        this.calculateCode = calculateCode;
        this.method = method;
        this.price = price;
    }

    public enum Method {
        MOBILE("휴대폰"),
        CREDIT_CARD("신용카드"),
        CASH("현금");

        private String text;

        Method(String text) { this.text = text; }

        public String getText() { return text; }
    }
}
