package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.ReservationEventType;
import com.sevketbuyukdemir.inventory_service.constant.ReservationStatus;
import lombok.Data;

@Data
public class KafkaReservationDTO {
    private ReservationEventType eventType;
    private long orderId;
    private long productId;
    private int quantity;
    private ReservationStatus status;

    public KafkaReservationDTO(ReservationEventType eventType, ReservationDTO reservation) {
        this.eventType = eventType;
        this.orderId = reservation.getOrderId();
        this.productId = reservation.getProductId();
        this.quantity = reservation.getQuantity();
        this.status = reservation.getStatus();
    }
}
