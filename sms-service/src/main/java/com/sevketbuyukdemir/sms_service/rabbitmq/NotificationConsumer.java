package com.sevketbuyukdemir.sms_service.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.sms_service.dto.NotificationRequestDTO;
import com.sevketbuyukdemir.sms_service.service.SMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final ObjectMapper objectMapper;
    private final SMSService smsService;

    @RabbitListener(queues = "sms-queue")
    public void receiveMessage(String message) throws JsonProcessingException {
        NotificationRequestDTO dto = objectMapper.readValue(message, NotificationRequestDTO.class);
        smsService.sendSMS(dto);
    }
}
