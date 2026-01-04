package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequestDTO {
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String currency;
    private String provider;

    public PaymentRequestDTO() {
    }

    public PaymentRequestDTO(Order order) {
        this.orderId = order.getId();
        this.userEmail = order.getUserEmail();
        this.amount = order.getTotalAmount();
        this.currency = order.getCurrency();
        this.provider = "PAYMENT_PROVIDER";
    }
}
