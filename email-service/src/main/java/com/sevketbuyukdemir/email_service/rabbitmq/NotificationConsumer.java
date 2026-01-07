package com.sevketbuyukdemir.email_service.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.email_service.dto.NotificationRequestDTO;
import com.sevketbuyukdemir.email_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void receiveMessage(String message) throws JsonProcessingException {
        NotificationRequestDTO dto = objectMapper.readValue(message, NotificationRequestDTO.class);
        emailService.sendEmail(dto);
    }
}
