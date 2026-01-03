package com.sevketbuyukdemir.order_service.controller;

import com.sevketbuyukdemir.order_service.request.CreateOrderRequest;
import com.sevketbuyukdemir.order_service.response.BaseResponse;
import com.sevketbuyukdemir.order_service.response.CreateOrderResponse;
import com.sevketbuyukdemir.order_service.response.GetOrderResponse;
import com.sevketbuyukdemir.order_service.response.GetOrdersResponse;
import com.sevketbuyukdemir.order_service.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BaseResponse> createOrder(Authentication authentication, HttpServletRequest request, @RequestBody CreateOrderRequest requestBody) {
        CreateOrderResponse response = orderService.createOrder(requestBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{orderId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BaseResponse> getOrder(Authentication authentication, HttpServletRequest request, @PathVariable("orderId") Long orderId) {
        GetOrderResponse response = orderService.getOrder(orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BaseResponse> getOrders(Authentication authentication, HttpServletRequest request) {
        GetOrdersResponse response = orderService.getOrders();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
