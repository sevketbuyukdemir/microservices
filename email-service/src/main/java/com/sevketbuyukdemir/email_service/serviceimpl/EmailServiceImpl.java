package com.sevketbuyukdemir.email_service.serviceimpl;

import com.sevketbuyukdemir.email_service.dto.NotificationRequestDTO;
import com.sevketbuyukdemir.email_service.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(NotificationRequestDTO request) {
        // Send email logic (AWS SES, Custom SMTP integration etc.)
        System.out.println(request.toString());
    }
}
