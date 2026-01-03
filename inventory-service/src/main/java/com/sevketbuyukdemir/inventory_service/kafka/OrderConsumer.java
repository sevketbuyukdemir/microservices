package com.sevketbuyukdemir.inventory_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.inventory_service.constant.OrderEventType;
import com.sevketbuyukdemir.inventory_service.constant.ReservationEventType;
import com.sevketbuyukdemir.inventory_service.dto.KafkaOrderDTO;
import com.sevketbuyukdemir.inventory_service.event.ReservationEvent;
import com.sevketbuyukdemir.inventory_service.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConsumer {
    private final ApplicationEventPublisher eventPublisher;
    private final ReservationService reservationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-topic")
    public void consume(@Payload String message) {
        try {
            KafkaOrderDTO dto = objectMapper.readValue(message, KafkaOrderDTO.class);
            long orderId = dto.getOrderId();
            if (OrderEventType.ORDER_INVENTORY_PENDING.equals(dto.getEventType())) {
                boolean result = reservationService.reserveStock(orderId, dto.getItems());
                ReservationEvent reservationEvent;
                if (result) {
                    reservationEvent = new ReservationEvent(this, ReservationEventType.STOCK_RESERVED, orderId);
                } else {
                    reservationEvent = new ReservationEvent(this, ReservationEventType.STOCK_RESERVATION_FAILED, orderId);
                }
                eventPublisher.publishEvent(reservationEvent);
            } else if (OrderEventType.ORDER_COMPLETED.equals(dto.getEventType())) {
                reservationService.removeReserved(orderId, dto.getItems());
            } else if (OrderEventType.ORDER_INVENTORY_REJECTED.equals(dto.getEventType()) || OrderEventType.ORDER_PAYMENT_REJECTED.equals(dto.getEventType())) {
                reservationService.releaseStock(orderId, dto.getItems());
                ReservationEvent reservationEvent = new ReservationEvent(this, ReservationEventType.STOCK_RELEASED, orderId);
                eventPublisher.publishEvent(reservationEvent);
            }
        } catch (Exception exception) {

        }
    }
}
