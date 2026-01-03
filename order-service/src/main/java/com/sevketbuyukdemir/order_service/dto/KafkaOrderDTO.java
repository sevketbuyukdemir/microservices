package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import lombok.Data;

import java.util.List;

@Data
public class KafkaOrderDTO {
    private OrderEventType eventType;
    private Long orderId;
    private List<OrderItemDTO> items;

    public KafkaOrderDTO(OrderEventType eventType, long orderId, OrderDTO orderDTO) {
        this.eventType = eventType;
        this.orderId = orderId;
        this.items = orderDTO.getItems();
    }
}
