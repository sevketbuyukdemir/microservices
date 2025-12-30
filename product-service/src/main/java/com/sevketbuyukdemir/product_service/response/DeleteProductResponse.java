package com.sevketbuyukdemir.product_service.response;

import com.sevketbuyukdemir.product_service.constant.ResponseStatusMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class DeleteProductResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "Product is deleted successfully.";
}