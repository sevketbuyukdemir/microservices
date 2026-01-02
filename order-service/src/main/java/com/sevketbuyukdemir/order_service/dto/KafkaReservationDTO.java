package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.constant.ReservationEventType;
import com.sevketbuyukdemir.order_service.constant.ReservationStatus;
import lombok.Data;

@Data
public class KafkaReservationDTO {
    private ReservationEventType eventType;
    private long orderId;
    private long productId;
    private int quantity;
    private ReservationStatus status;
}

