package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.ReservationStatus;
import com.sevketbuyukdemir.inventory_service.entity.Reservation;
import lombok.Data;

@Data
public class ReservationDTO {
    private long orderId;
    private long productId;
    private int quantity;
    private ReservationStatus status;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation) {
        this.orderId = reservation.getOrderId();
        this.productId = reservation.getProductId();
        this.quantity = reservation.getQuantity();
        this.status = reservation.getStatus();
    }
}
