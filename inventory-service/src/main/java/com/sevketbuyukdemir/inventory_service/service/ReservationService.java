package com.sevketbuyukdemir.inventory_service.service;

import com.sevketbuyukdemir.inventory_service.dto.OrderItemDTO;

import java.util.List;

public interface ReservationService {
    boolean reserveStock(long orderId, List<OrderItemDTO> items);
    void removeReserved(long orderId, List<OrderItemDTO> items);
    void releaseStock(long orderId, List<OrderItemDTO> items);
}
