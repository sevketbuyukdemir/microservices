package com.sevketbuyukdemir.order_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequestDTO {
    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
}
