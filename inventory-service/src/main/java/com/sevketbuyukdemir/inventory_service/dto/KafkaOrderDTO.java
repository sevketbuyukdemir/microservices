package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.OrderEventType;
import lombok.Data;

import java.util.List;

@Data
public class KafkaOrderDTO {
    private OrderEventType eventType;
    private Long orderId;
    private List<OrderItemDTO> items;
}
