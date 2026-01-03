package com.sevketbuyukdemir.order_service.response;

import com.sevketbuyukdemir.order_service.constant.ResponseStatusMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CancelOrderResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "Order is cancelled successfully.";
}
