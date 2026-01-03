package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import com.sevketbuyukdemir.order_service.entity.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventDTO {
    private OrderEventType eventType;
    private LocalDateTime createdAt;

    public OrderEventDTO(OrderEvent orderEvent) {
        this.eventType = orderEvent.getEventType();
        this.createdAt = orderEvent.getCreatedAt();
    }
}
