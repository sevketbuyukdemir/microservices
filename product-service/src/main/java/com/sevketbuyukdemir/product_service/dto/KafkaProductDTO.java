package com.sevketbuyukdemir.product_service.dto;

import com.sevketbuyukdemir.product_service.constant.ProductEventType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class KafkaProductDTO {
    private ProductEventType eventType;
    private long productId;
    private String name;
    private BigDecimal price;
    private String currency;
    private Boolean active;

    public KafkaProductDTO(ProductEventType eventType, long productId, ProductDTO product) {
        this.eventType = eventType;
        this.productId = productId;
        this.name = product.getName();
        this.price = product.getPrice();
        this.currency = product.getCurrency();
        this.active = product.getActive();
    }
}
