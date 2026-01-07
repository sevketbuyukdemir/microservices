package com.sevketbuyukdemir.sms_service.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sevketbuyukdemir.sms_service.dto.NotificationRequestDTO;
import com.sevketbuyukdemir.sms_service.service.SMSService;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendSMS(NotificationRequestDTO request) throws JsonProcessingException {
        // Send SMS logic
        System.out.println(request.toString());
    }
}
