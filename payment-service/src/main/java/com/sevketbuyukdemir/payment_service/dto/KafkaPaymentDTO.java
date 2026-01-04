package com.sevketbuyukdemir.payment_service.dto;

import com.sevketbuyukdemir.payment_service.constant.PaymentEventType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class KafkaPaymentDTO {
    private PaymentEventType eventType;
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String currency;
    private String provider;

    public KafkaPaymentDTO(PaymentEventType eventType, PaymentRequestDTO paymentRequestDTO) {
        this.eventType = eventType;
        this.orderId = paymentRequestDTO.getOrderId();
        this.userEmail = paymentRequestDTO.getUserEmail();
        this.amount = paymentRequestDTO.getAmount();
        this.currency = paymentRequestDTO.getCurrency();
        this.provider = paymentRequestDTO.getProvider();
    }
}
