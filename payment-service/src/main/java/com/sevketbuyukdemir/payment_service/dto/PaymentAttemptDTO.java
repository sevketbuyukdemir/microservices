package com.sevketbuyukdemir.payment_service.dto;

import com.sevketbuyukdemir.payment_service.entity.Payment;
import com.sevketbuyukdemir.payment_service.util.IdempotencyKeyGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAttemptDTO {
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String currency;
    private String provider;
    private String idempotencyKey;

    public PaymentAttemptDTO(PaymentRequestDTO paymentRequestDTO) {
        this.orderId = paymentRequestDTO.getOrderId();
        this.userEmail = paymentRequestDTO.getUserEmail();
        this.amount = paymentRequestDTO.getAmount();
        this.currency = paymentRequestDTO.getCurrency();
        this.provider = paymentRequestDTO.getProvider();
        this.idempotencyKey = IdempotencyKeyGenerator.generateKey();
    }

    public PaymentAttemptDTO(Payment payment, String idempotencyKey) {
        this.orderId = payment.getOrderId();
        this.userEmail = payment.getUserEmail();
        this.amount = payment.getAmount();
        this.currency = payment.getCurrency();
        this.provider = payment.getProvider();
        this.idempotencyKey = idempotencyKey;
    }
}