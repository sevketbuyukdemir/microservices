package com.sevketbuyukdemir.order_service.rabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.order_service.dto.PaymentRequestDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(String exchange, String routingKey, PaymentRequestDTO dto) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(dto);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}