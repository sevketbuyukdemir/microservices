package com.sevketbuyukdemir.order_service.response;

import com.sevketbuyukdemir.order_service.constant.ResponseStatusMessage;
import com.sevketbuyukdemir.order_service.dto.OrderDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetOrdersResponse extends BaseResponse {
    private String status = ResponseStatusMessage.SUCCESS.toLower();
    private String message = "You can find orders from orders.";
    private List<OrderDTO> orders;

    public GetOrdersResponse(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
