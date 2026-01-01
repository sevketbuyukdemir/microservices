package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.ProductEventType;
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
}
