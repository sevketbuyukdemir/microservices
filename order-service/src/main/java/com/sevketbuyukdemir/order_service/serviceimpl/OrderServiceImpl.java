package com.sevketbuyukdemir.order_service.serviceimpl;

import com.sevketbuyukdemir.order_service.constant.OrderEventType;
import com.sevketbuyukdemir.order_service.constant.OrderStatus;
import com.sevketbuyukdemir.order_service.dto.OrderDTO;
import com.sevketbuyukdemir.order_service.entity.Order;
import com.sevketbuyukdemir.order_service.entity.OrderEvent;
import com.sevketbuyukdemir.order_service.event.OrderApplicationEvent;
import com.sevketbuyukdemir.order_service.repository.OrderItemRepository;
import com.sevketbuyukdemir.order_service.repository.OrderRepository;
import com.sevketbuyukdemir.order_service.request.CreateOrderRequest;
import com.sevketbuyukdemir.order_service.response.CreateOrderResponse;
import com.sevketbuyukdemir.order_service.response.GetOrderResponse;
import com.sevketbuyukdemir.order_service.response.GetOrdersResponse;
import com.sevketbuyukdemir.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ApplicationEventPublisher eventPublisher;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        Order order = new Order("todo@iam.com", request);
        order.setStatus(OrderStatus.INVENTORY_PENDING);
        order.addEvent(new OrderEvent(OrderEventType.ORDER_INVENTORY_PENDING));
        Order createdOrder = orderRepository.save(order);
        OrderApplicationEvent event = new OrderApplicationEvent(this, OrderEventType.ORDER_INVENTORY_PENDING, createdOrder);
        eventPublisher.publishEvent(event);
        return new CreateOrderResponse();
    }

    @Override
    public GetOrderResponse getOrder(long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return new GetOrderResponse(new OrderDTO(order));
    }

    @Override
    public GetOrdersResponse getOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = orders.stream().map(OrderDTO::new).collect(Collectors.toList());
        return new GetOrdersResponse(orderDTOs);
    }
}
