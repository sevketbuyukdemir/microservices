package com.sevketbuyukdemir.payment_service.service;

import com.sevketbuyukdemir.payment_service.dto.PaymentAttemptDTO;

public interface PaymentService {
    void processPayment(PaymentAttemptDTO attemptDTO);
}
