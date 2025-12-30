package com.sevketbuyukdemir.product_service.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sevketbuyukdemir.product_service.dto.ProductCategoryDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory(ProductCategoryDTO dto) {
        this.name = dto.getName();
    }
}