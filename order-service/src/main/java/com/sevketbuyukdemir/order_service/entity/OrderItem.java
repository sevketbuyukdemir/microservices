package com.sevketbuyukdemir.order_service.entity;


import com.sevketbuyukdemir.order_service.dto.OrderItemRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 4)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "line_total", nullable = false, precision = 19, scale = 4)
    private BigDecimal lineTotal;

    public OrderItem() {}

    public OrderItem(OrderItemRequestDTO request) {
        this.productId = request.getProductId();
        this.productName = request.getProductName();
        this.unitPrice = request.getUnitPrice();
        this.quantity = request.getQuantity();
        this.lineTotal = unitPrice.multiply(new BigDecimal(quantity));
    }
}
