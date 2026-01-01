package com.sevketbuyukdemir.product_service.dto;

import com.sevketbuyukdemir.product_service.constant.ProductEventType;
import lombok.Data;

@Data
public class KafkaProductDTO {
    private ProductEventType eventType;
    private long productId;
    private ProductDTO product;

    public KafkaProductDTO(ProductEventType eventType, long productId, ProductDTO product) {
        this.eventType = eventType;
        this.productId = productId;
        this.product = product;
    }
}
