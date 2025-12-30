package com.sevketbuyukdemir.product_service.exception;

import com.sevketbuyukdemir.product_service.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionResponse extends BaseResponse {
    private String status;
    private String message;
}
