package com.sevketbuyukdemir.product_service.dto;

import com.sevketbuyukdemir.product_service.entity.ProductAttribute;
import lombok.Data;

@Data
public class ProductAttributeDTO {
    private String key;
    private String value;

    public ProductAttributeDTO() {
    }

    public ProductAttributeDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ProductAttributeDTO(ProductAttribute attribute) {
        this.key = attribute.getAttrType();
        this.value = attribute.getAttrValue();
    }
}
