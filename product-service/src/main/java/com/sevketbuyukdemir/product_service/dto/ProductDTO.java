package com.sevketbuyukdemir.product_service.dto;

import com.sevketbuyukdemir.product_service.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String currency;
    private Boolean active = true;
    private Set<ProductCategoryDTO> categories;
    private Set<ProductAttributeDTO> attributes;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.currency = product.getCurrency();
        this.active = product.getActive();
        this.categories = product.getCategories().stream().map(ProductCategoryDTO::new).collect(Collectors.toSet());
        this.attributes = product.getAttributes().stream().map(ProductAttributeDTO::new).collect(Collectors.toSet());
    }
}
