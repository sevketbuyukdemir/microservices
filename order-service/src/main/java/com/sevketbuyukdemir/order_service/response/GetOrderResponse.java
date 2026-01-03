package com.sevketbuyukdemir.order_service.response;

import com.sevketbuyukdemir.order_service.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.order_service.dto.OrderDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetOrderResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "You can find order details from order item.";
    private OrderDTO order;

    public GetOrderResponse(OrderDTO order) {
        this.order = order;
    }
}
