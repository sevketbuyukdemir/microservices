package com.sevketbuyukdemir.product_service.response;

import com.sevketbuyukdemir.product_service.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "Product is updated successfully.";
    private ProductDTO product;

    public UpdateProductResponse(ProductDTO product) {
        this.product = product;
    }
}