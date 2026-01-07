package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.constant.PaymentEventType;
import com.sevketbuyukdemir.order_service.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class NotificationRequestDTO {
    private PaymentEventType eventType;
    private Long orderId;
    private String userEmail;
    private BigDecimal amount;
    private String currency;
    private String provider;
    private List<OrderItemDTO> orderItems;

    public NotificationRequestDTO(Order order, KafkaPaymentDTO paymentDTO) {
        this.eventType = paymentDTO.getEventType();
        this.orderId = order.getId();
        this.userEmail = order.getUserEmail();
        this.amount = order.getTotalAmount();
        this.currency = order.getCurrency();
        this.provider = paymentDTO.getProvider();
        this.orderItems = order.getItems().stream().map(OrderItemDTO::new).collect(Collectors.toList());
    }
}
