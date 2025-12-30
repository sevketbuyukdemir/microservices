package com.sevketbuyukdemir.product_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import com.sevketbuyukdemir.product_service.request.UpdateProductRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @JsonIgnore
    private String name;

    @Column(name = "description")
    @JsonIgnore
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "TRY";

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_category_map",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private Set<ProductCategory> categories;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_attribute_map",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id")}
    )
    private Set<ProductAttribute> attributes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(ProductDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.currency = dto.getCurrency();
        this.active = dto.getActive();
        this.categories = dto.getCategories().stream().map(ProductCategory::new).collect(Collectors.toSet());
        this.attributes = dto.getAttributes().stream().map(ProductAttribute::new).collect(Collectors.toSet());
    }

    public void updateProduct(UpdateProductRequest request) {
        this.description = request.getDescription();
        this.price = request.getPrice();
        this.currency = request.getCurrency();
        this.active = request.getActive();
        this.categories = request.getCategories().stream().map(ProductCategory::new).collect(Collectors.toSet());
        this.attributes = request.getAttributes().stream().map(ProductAttribute::new).collect(Collectors.toSet());
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
