package com.sevketbuyukdemir.product_service.dto;

import com.sevketbuyukdemir.product_service.entity.ProductCategory;
import lombok.Data;

@Data
public class ProductCategoryDTO {
    private String name;

    public ProductCategoryDTO() {
    }

    public ProductCategoryDTO(String name) {
        this.name = name;
    }

    public ProductCategoryDTO(ProductCategory category) {
        this.name = category.getName();
    }
}
