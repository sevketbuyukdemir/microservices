package com.sevketbuyukdemir.order_service.event;

import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import com.sevketbuyukdemir.order_service.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderApplicationEvent extends ApplicationEvent {
    private final OrderEventType type;
    private final Order order;

    public OrderApplicationEvent(Object source, OrderEventType type, Order order) {
        super(source);
        this.type = type;
        this.order = order;
    }
}
