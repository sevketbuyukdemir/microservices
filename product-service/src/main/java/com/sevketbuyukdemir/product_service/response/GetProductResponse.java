package com.sevketbuyukdemir.product_service.response;

import com.sevketbuyukdemir.product_service.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetProductResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "You can find product from product item.";
    private ProductDTO product;

    public GetProductResponse(ProductDTO product) {
        this.product = product;
    }
}
