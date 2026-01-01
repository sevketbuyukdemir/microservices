package com.sevketbuyukdemir.inventory_service.eventlistener;

import com.sevketbuyukdemir.inventory_service.event.ReservationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationEventListener {
    @EventListener
    public void handleReservationEvent(ReservationEvent reservationEvent) {}
}
