package com.sevketbuyukdemir.sms_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sevketbuyukdemir.sms_service.dto.NotificationRequestDTO;

public interface SMSService {
    void sendSMS(NotificationRequestDTO request) throws JsonProcessingException;
}
