package com.sevketbuyukdemir.product_service.request;

import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import lombok.Data;


@Data
public class CreateProductRequest {
    private ProductDTO product;
}
