package com.sevketbuyukdemir.inventory_service.eventlistener;

import com.sevketbuyukdemir.inventory_service.constant.ReservationEventType;
import com.sevketbuyukdemir.inventory_service.dto.KafkaReservationDTO;
import com.sevketbuyukdemir.inventory_service.event.ReservationEvent;
import com.sevketbuyukdemir.inventory_service.kafka.ReservationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationEventListener {
    private final ReservationProducer reservationProducer;

    @EventListener
    public void handleReservationEvent(ReservationEvent reservationEvent) {
        ReservationEventType type = reservationEvent.getType();
        long orderId = reservationEvent.getOrderId();
        KafkaReservationDTO dto = new KafkaReservationDTO(type, orderId);
        reservationProducer.send(dto);
    }
}
