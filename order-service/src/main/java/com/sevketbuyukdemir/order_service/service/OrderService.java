package com.sevketbuyukdemir.order_service.service;

import com.sevketbuyukdemir.order_service.request.CreateOrderRequest;
import com.sevketbuyukdemir.order_service.response.CreateOrderResponse;
import com.sevketbuyukdemir.order_service.response.GetOrderResponse;
import com.sevketbuyukdemir.order_service.response.GetOrdersResponse;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest request);
    GetOrderResponse getOrder(long orderId);
    GetOrdersResponse getOrders();
}
