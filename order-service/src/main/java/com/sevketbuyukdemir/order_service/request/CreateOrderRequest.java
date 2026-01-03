package com.sevketbuyukdemir.order_service.request;

import com.sevketbuyukdemir.order_service.dto.OrderItemRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private String currency;
    private List<OrderItemRequestDTO> items;
}
