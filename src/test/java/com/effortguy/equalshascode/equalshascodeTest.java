package com.effortguy.equalshascode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class equalshascodeTest {

    private static List<Payment> payments;

    @BeforeAll
    static void setup() {
        //given
        payments = Arrays.asList(
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2017, 3, 23))
                        .calculateCode("C001")
                        .price(20000)
                        .method(Payment.Method.MOBILE)
                        .build(),
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2017, 3, 23))
                        .calculateCode("C001")
                        .price(10000)
                        .method(Payment.Method.CREDIT_CARD)
                        .build(),
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2017, 3, 23))
                        .calculateCode("C001")
                        .price(5000)
                        .method(Payment.Method.CASH)
                        .build(),
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2017, 3, 23))
                        .calculateCode("C002")
                        .price(20000)
                        .method(Payment.Method.MOBILE)
                        .build(),
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2017, 3, 23))
                        .calculateCode("C001")
                        .price(20000)
                        .method(Payment.Method.CREDIT_CARD)
                        .build(),
                Payment.builder()
                        .ownerId(1L)
                        .payDate(LocalDate.of(2017, 3, 23))
                        .calculateCode("C002")
                        .price(30000)
                        .method(Payment.Method.MOBILE)
                        .build()
        );
    }

    @Test
    void test_paymentDto_equals() {
        Payment payment1 = Payment.builder()
                .ownerId(1L)
                .payDate(LocalDate.of(2017, 3, 23))
                .calculateCode("C001")
                .price(20000)
                .method(Payment.Method.MOBILE)
                .build();

        Payment payment2 = Payment.builder()
                .ownerId(1L)
                .payDate(LocalDate.of(2017, 3, 23))
                .calculateCode("C002")
                .price(20000)
                .method(Payment.Method.MOBILE)
                .build();

        PaymentDto paymentDto = new PaymentDto(payment1);
        PaymentDto paymentDto2 = new PaymentDto(payment2);

        assertThat(paymentDto.equals(paymentDto2), is(false));
        assertThat(paymentDto.hashCode() == paymentDto2.hashCode(), is(false));
    }

    @Test
    void test_payment_분류() {
        //given
        List<List<PaymentDto>> classified = PaymentDto.classify(payments).collect(Collectors.toList());
        List<PaymentDto> firstPaymentDtos = classified.get(0);


        //expected
        assertThat(classified.size(), is(4));
        assertThat(firstPaymentDtos.size(), is(3));
        assertThat(firstPaymentDtos.get(0).getPaymentMethod(), is(Payment.Method.MOBILE));
        assertThat(firstPaymentDtos.get(0).getPaymentMethod(), is(Payment.Method.CREDIT_CARD));
        assertThat(firstPaymentDtos.get(0).getPaymentMethod(), is(Payment.Method.CASH));
    }

    @Test
    void test_payment_to_sales() {
        List<Sales> salesList = SalesConverter.createSalesList(PaymentDto.classify(payments));
        Sales sales = salesList.get(0);

        sales.hashCode();

        assertThat(sales.getTotalAmount(), is(35000));
        assertThat(sales.getMobileAmount(), is(20000));
        assertThat(sales.getCreditCardAmount(), is(10000));
        assertThat(sales.getCashAmount(), is(5000));

    }

    @Test
    void test_student_equals() {
        Student student = Student.builder().id("effort").name("guy").build();
        Student student2 = Student.builder().id("effort").name("guy").build();

        assertTrue(student.equals(student2));
    }

    @Test
    void test_student_hashcode() {
        Student student = Student.builder().id("effort").name("guy").build();
        Student student2 = Student.builder().id("effort").name("guy").build();

        Map<Student, String> studentMap = new HashMap();

        studentMap.put(student, student.getName());
        studentMap.put(student2, student.getName());

        assertTrue(student.equals(student2));
        assertThat(studentMap.size(), is(1));
    }

    @Test
    void test_student_toString() {
        Student student = new Student("effort", "guy");

        System.out.println(student.toString());
    }
}