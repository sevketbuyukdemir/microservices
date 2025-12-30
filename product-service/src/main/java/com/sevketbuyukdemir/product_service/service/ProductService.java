package com.sevketbuyukdemir.product_service.service;

import com.sevketbuyukdemir.product_service.request.CreateProductRequest;
import com.sevketbuyukdemir.product_service.request.UpdateProductRequest;
import com.sevketbuyukdemir.product_service.response.*;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest request);
    GetProductResponse readProduct(String name);
    GetProductsResponse readProducts();
    UpdateProductResponse updateProduct(String name, UpdateProductRequest request);
    DeleteProductResponse deleteProduct(String name);
}
