package com.sevketbuyukdemir.email_service.dto;

import com.sevketbuyukdemir.email_service.constant.PaymentEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    private PaymentEventType eventType;
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String currency;
    private String provider;
    private List<OrderItemDTO> orderItems;

    @Override
    public String toString() {
        return "NotificationRequestDTO{" +
                "eventType=" + eventType +
                ", orderId=" + orderId +
                ", userEmail='" + userEmail + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", provider='" + provider + '\'' +
                '}';
    }
}

