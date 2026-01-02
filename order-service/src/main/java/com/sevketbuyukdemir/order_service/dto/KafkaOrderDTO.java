package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import lombok.Data;

import java.util.List;

@Data
public class KafkaOrderDTO {
    private OrderEventType eventType;
    private Long id;
    private List<OrderItemDTO> items;

    public KafkaOrderDTO(OrderEventType eventType, OrderDTO orderDTO) {
        this.eventType = eventType;
        this.id = orderDTO.getId();
        this.items = orderDTO.getItems();
    }
}
