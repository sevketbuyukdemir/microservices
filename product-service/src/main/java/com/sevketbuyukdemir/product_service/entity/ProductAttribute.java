package com.sevketbuyukdemir.product_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sevketbuyukdemir.product_service.dto.ProductAttributeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_attributes")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "attrType")
    private String attrType;

    @Column(name = "attrValue")
    private String attrValue;

    public ProductAttribute() {
    }

    public ProductAttribute(ProductAttributeDTO dto) {
        this.attrType = dto.getKey();
        this.attrValue = dto.getValue();
    }
}