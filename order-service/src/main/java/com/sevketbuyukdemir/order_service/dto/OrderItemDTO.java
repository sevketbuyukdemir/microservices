package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.entity.OrderItem;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal lineTotal;

    public OrderItemDTO(OrderItem orderItem) {
        this.productId = orderItem.getProductId();
        this.productName = orderItem.getProductName();
        this.unitPrice = orderItem.getUnitPrice();
        this.quantity = orderItem.getQuantity();
        this.lineTotal = orderItem.getLineTotal();
    }
}