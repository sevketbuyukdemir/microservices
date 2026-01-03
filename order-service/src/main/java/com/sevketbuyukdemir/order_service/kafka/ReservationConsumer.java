package com.sevketbuyukdemir.order_service.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import com.sevketbuyukdemir.order_service.constant.OrderStatus;
import com.sevketbuyukdemir.order_service.constant.ReservationEventType;
import com.sevketbuyukdemir.order_service.dto.KafkaReservationDTO;
import com.sevketbuyukdemir.order_service.entity.Order;
import com.sevketbuyukdemir.order_service.entity.OrderEvent;
import com.sevketbuyukdemir.order_service.event.OrderApplicationEvent;
import com.sevketbuyukdemir.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationConsumer {
    private final ApplicationEventPublisher eventPublisher;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "reservation-topic")
    public void consume(@Payload String message) {
        try {
            KafkaReservationDTO dto = objectMapper.readValue(message, KafkaReservationDTO.class);
            if (ReservationEventType.STOCK_RESERVED.equals(dto.getEventType())) {
                Order order = orderRepository.findById(dto.getOrderId()).get();
                order.setStatus(OrderStatus.PAYMENT_PENDING);
                order.addEvent(new OrderEvent(OrderEventType.ORDER_PAYMENT_PENDING));
                orderRepository.save(order);
                System.out.println("Process payment for order: " + dto.getOrderId());
            } else if (ReservationEventType.STOCK_RESERVATION_FAILED.equals(dto.getEventType())) {
                Order order = orderRepository.findById(dto.getOrderId()).get();
                order.setStatus(OrderStatus.INVENTORY_REJECTED);
                order.addEvent(new OrderEvent(OrderEventType.ORDER_INVENTORY_REJECTED));
                orderRepository.save(order);
                OrderApplicationEvent event = new OrderApplicationEvent(this, OrderEventType.ORDER_INVENTORY_REJECTED, order);
                eventPublisher.publishEvent(event);
            }
        } catch (Exception e) {

        }
    }
}
