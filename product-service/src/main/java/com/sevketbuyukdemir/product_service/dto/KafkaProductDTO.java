package com.sevketbuyukdemir.product_service.dto;

import com.sevketbuyukdemir.product_service.constant.ProductEventType;
import lombok.Data;

@Data
public class KafkaProductDTO {
    private ProductEventType eventType;
    private ProductDTO product;

    public KafkaProductDTO(ProductEventType eventType, ProductDTO product) {
        this.eventType = eventType;
        this.product = product;
    }
}
