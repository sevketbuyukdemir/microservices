package com.sevketbuyukdemir.product_service.request;

import com.sevketbuyukdemir.product_service.dto.ProductAttributeDTO;
import com.sevketbuyukdemir.product_service.dto.ProductCategoryDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class UpdateProductRequest {
    private String description;
    private BigDecimal price;
    private String currency;
    private Boolean active = true;
    private Set<ProductCategoryDTO> categories;
    private Set<ProductAttributeDTO> attributes;
}
