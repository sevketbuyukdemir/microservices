package com.sevketbuyukdemir.product_service.response;

import com.sevketbuyukdemir.product_service.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreateProductResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "Product is created successfully.";
    private ProductDTO product;

    public CreateProductResponse(ProductDTO product) {
        this.product = product;
    }
}