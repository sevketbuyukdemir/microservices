package com.sevketbuyukdemir.inventory_service.event;

import com.sevketbuyukdemir.inventory_service.constant.ReservationEventType;
import com.sevketbuyukdemir.inventory_service.entity.Reservation;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ReservationEvent extends ApplicationEvent {
    private final ReservationEventType type;
    private final Reservation reservation;

    public ReservationEvent(Object source, ReservationEventType type, Reservation reservation) {
        super(source);
        this.type = type;
        this.reservation = reservation;
    }
}
