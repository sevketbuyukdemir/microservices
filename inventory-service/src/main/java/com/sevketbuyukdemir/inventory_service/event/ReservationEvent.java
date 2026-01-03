package com.sevketbuyukdemir.inventory_service.event;

import com.sevketbuyukdemir.inventory_service.constant.ReservationEventType;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ReservationEvent extends ApplicationEvent {
    private final ReservationEventType type;
    private final Long orderId;

    public ReservationEvent(Object source, ReservationEventType type, Long orderId) {
        super(source);
        this.type = type;
        this.orderId = orderId;
    }
}
