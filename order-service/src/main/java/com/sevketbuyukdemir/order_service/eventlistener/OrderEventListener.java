package com.sevketbuyukdemir.order_service.eventlistener;

import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import com.sevketbuyukdemir.order_service.dto.KafkaOrderDTO;
import com.sevketbuyukdemir.order_service.dto.OrderDTO;
import com.sevketbuyukdemir.order_service.entity.Order;
import com.sevketbuyukdemir.order_service.event.OrderApplicationEvent;
import com.sevketbuyukdemir.order_service.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderEventListener {
    private final OrderProducer orderProducer;

    @EventListener
    public void handleInventoryEvent(OrderApplicationEvent orderApplicationEvent) {
        OrderEventType type = orderApplicationEvent.getType();
        Order order = orderApplicationEvent.getOrder();
        OrderDTO orderDTO = new OrderDTO(order);
        KafkaOrderDTO dto = new KafkaOrderDTO(type, order.getId(), orderDTO);
        orderProducer.send(dto);
    }
}