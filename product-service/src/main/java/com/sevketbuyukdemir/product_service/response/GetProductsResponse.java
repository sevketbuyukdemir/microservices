package com.sevketbuyukdemir.product_service.response;

import com.sevketbuyukdemir.product_service.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetProductsResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "You can find products from products item.";
    private List<ProductDTO> products;

    public GetProductsResponse(List<ProductDTO> products) {
        this.products = products;
    }
}
