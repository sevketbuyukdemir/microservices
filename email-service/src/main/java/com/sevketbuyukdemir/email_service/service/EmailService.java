package com.sevketbuyukdemir.email_service.service;

import com.sevketbuyukdemir.email_service.dto.NotificationRequestDTO;

public interface EmailService {
    void sendEmail(NotificationRequestDTO request);
}
