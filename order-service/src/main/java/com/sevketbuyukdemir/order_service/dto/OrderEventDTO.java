package com.sevketbuyukdemir.order_service.dto;

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

    private Long id;

    private String eventType;

    private LocalDateTime createdAt;

    public OrderEventDTO(OrderEvent orderEvent) {
        this.id = orderEvent.getId();
        this.eventType = orderEvent.getEventType();
        this.createdAt = orderEvent.getCreatedAt();
    }
}
