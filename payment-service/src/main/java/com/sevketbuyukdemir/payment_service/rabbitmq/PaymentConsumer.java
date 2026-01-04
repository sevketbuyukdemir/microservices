package com.sevketbuyukdemir.payment_service.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.payment_service.dto.PaymentAttemptDTO;
import com.sevketbuyukdemir.payment_service.dto.PaymentRequestDTO;
import com.sevketbuyukdemir.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "payment-queue")
    public void orderQueueListener(String message) throws JsonProcessingException {
        PaymentRequestDTO dto = objectMapper.readValue(message, PaymentRequestDTO.class);
        paymentService.processPayment(new PaymentAttemptDTO(dto));
    }
}
