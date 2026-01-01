package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.ReservationEventType;
import lombok.Data;

@Data
public class KafkaReservationDTO {
    private ReservationEventType eventType;
    private ReservationDTO reservation;

    public KafkaReservationDTO(ReservationEventType eventType, ReservationDTO reservation) {
        this.eventType = eventType;
        this.reservation = reservation;
    }
}
