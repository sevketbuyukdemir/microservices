package com.sevketbuyukdemir.product_service.repository.custom;

import com.sevketbuyukdemir.product_service.entity.Product;

public interface ProductRepositoryCustom {
    Product saveWithRelations(Product product);
}
