package com.sevketbuyukdemir.order_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import com.sevketbuyukdemir.order_service.constant.OrderStatus;
import com.sevketbuyukdemir.order_service.constant.PaymentEventType;
import com.sevketbuyukdemir.order_service.dto.KafkaPaymentDTO;
import com.sevketbuyukdemir.order_service.dto.NotificationRequestDTO;
import com.sevketbuyukdemir.order_service.entity.Order;
import com.sevketbuyukdemir.order_service.entity.OrderEvent;
import com.sevketbuyukdemir.order_service.event.OrderApplicationEvent;
import com.sevketbuyukdemir.order_service.rabbitmq.NotificationProducer;
import com.sevketbuyukdemir.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {
    private final ApplicationEventPublisher eventPublisher;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final NotificationProducer notificationProducer;

    private final String RABBITMQ_EXCHANGE = "notification-exchange";
    private final String RABBITMQ_ALL_CHANNELS_ROUTING_KEY = "notify-via-all-channels";
    private final String RABBITMQ_EMAIL_CHANNEL_ROUTING_KEY = "notify-via-email-channel";

    @KafkaListener(topics = "payment-topic")
    public void consume(@Payload String message) {
        try {
            KafkaPaymentDTO dto = objectMapper.readValue(message, KafkaPaymentDTO.class);
            if (PaymentEventType.SUCCESS.equals(dto.getEventType())) {
                Order order = orderRepository.findById(dto.getOrderId()).get();
                order.setStatus(OrderStatus.COMPLETED);
                order.addEvent(new OrderEvent(OrderEventType.ORDER_COMPLETED));
                Order completedOrder = orderRepository.save(order);
                OrderApplicationEvent event = new OrderApplicationEvent(this, OrderEventType.ORDER_COMPLETED, completedOrder);
                eventPublisher.publishEvent(event);
                notificationProducer.sendMessage(RABBITMQ_EXCHANGE, RABBITMQ_EMAIL_CHANNEL_ROUTING_KEY, new NotificationRequestDTO(completedOrder, dto));
            } else if (PaymentEventType.FAILED.equals(dto.getEventType())) {
                Order order = orderRepository.findById(dto.getOrderId()).get();
                order.setStatus(OrderStatus.PAYMENT_REJECTED);
                order.addEvent(new OrderEvent(OrderEventType.ORDER_PAYMENT_REJECTED));
                Order rejectedOrder = orderRepository.save(order);
                OrderApplicationEvent event = new OrderApplicationEvent(this, OrderEventType.ORDER_PAYMENT_REJECTED, rejectedOrder);
                eventPublisher.publishEvent(event);
                notificationProducer.sendMessage(RABBITMQ_EXCHANGE, RABBITMQ_ALL_CHANNELS_ROUTING_KEY, new NotificationRequestDTO(rejectedOrder, dto));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
