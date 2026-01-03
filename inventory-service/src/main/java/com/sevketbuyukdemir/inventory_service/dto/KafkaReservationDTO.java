package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.ReservationEventType;
import lombok.Data;

@Data
public class KafkaReservationDTO {
    private ReservationEventType eventType;
    private long orderId;

    public KafkaReservationDTO(ReservationEventType eventType, Long orderId) {
        this.eventType = eventType;
        this.orderId = orderId;
    }
}
