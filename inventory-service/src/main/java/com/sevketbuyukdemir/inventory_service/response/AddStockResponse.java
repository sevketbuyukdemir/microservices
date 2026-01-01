package com.sevketbuyukdemir.inventory_service.response;

import com.sevketbuyukdemir.inventory_service.constant.ResponseStatusMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddStockResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "Stock is added successfully.";
}
