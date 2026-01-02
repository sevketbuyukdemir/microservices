package com.sevketbuyukdemir.order_service.dto;


import com.sevketbuyukdemir.order_service.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    private String userEmail;

    private String status;

    private String currency;

    private BigDecimal totalAmount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<OrderItemDTO> items;

    private List<OrderEventDTO> events;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.userEmail = order.getUserEmail();
        this.status = order.getStatus().toString();
        this.currency = order.getCurrency();
        this.totalAmount = order.getTotalAmount();
        this.items = order.getItems().stream().map(OrderItemDTO::new).collect(Collectors.toList());
        this.events = order.getEvents().stream().map(OrderEventDTO::new).collect(Collectors.toList());

    }
}
