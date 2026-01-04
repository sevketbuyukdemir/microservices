package com.sevketbuyukdemir.payment_service.dto;

import com.sevketbuyukdemir.payment_service.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String currency;
    private String provider;

    public PaymentRequestDTO(Payment payment) {
        this.orderId = payment.getOrderId();
        this.userEmail = payment.getUserEmail();
        this.amount = payment.getAmount();
        this.currency = payment.getCurrency();
        this.provider = payment.getProvider();
    }
}
