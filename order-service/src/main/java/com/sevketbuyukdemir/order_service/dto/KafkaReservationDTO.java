package com.sevketbuyukdemir.order_service.dto;

import com.sevketbuyukdemir.order_service.constant.ReservationEventType;
import lombok.Data;

@Data
public class KafkaReservationDTO {
    private ReservationEventType eventType;
    private long orderId;
}

