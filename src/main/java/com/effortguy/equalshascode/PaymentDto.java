package com.effortguy.equalshascode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

@Getter
@EqualsAndHashCode(exclude = {"id", "paymentMethod", "price"})
@ToString
public class PaymentDto {
    private Long id;
    private Long ownerId;
    private LocalDate payDate;
    private String calculateCode;
    private Payment.Method paymentMethod;
    private int price;

    public PaymentDto(Payment entity) {
        this.id = entity.getId();
        this.ownerId = entity.getOwnerId();
        this.payDate = entity.getPayDate();
        this.calculateCode = entity.getCalculateCode();
        this.paymentMethod = entity.getMethod();
        this.price = entity.getPrice();
    }

    public static Stream<List<PaymentDto>> classify(List<Payment> payments) {
        Map<PaymentDto, List<PaymentDto>> classifiedPayment = new LinkedHashMap();

        for (Payment payment : payments) {
            PaymentDto dto = new PaymentDto(payment);
            List<PaymentDto> list = classifiedPayment.get(dto);

            if(list != null) {
                list.add(dto);
            } else {
                classifiedPayment.put(dto, new ArrayList<>(Collections.singletonList(dto)));
            }
        }

        classifiedPayment.keySet().stream().forEach(key -> System.out.println(classifiedPayment.get(key).size()));

        return classifiedPayment.entrySet().stream()
                .map(Map.Entry::getValue);
    }
}
