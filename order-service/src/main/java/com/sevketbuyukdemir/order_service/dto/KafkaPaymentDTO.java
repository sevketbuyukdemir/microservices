package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.constant.PaymentEventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaPaymentDTO {
    private PaymentEventType eventType;
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String currency;
    private String provider;
}
