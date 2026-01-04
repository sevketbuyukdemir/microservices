package com.sevketbuyukdemir.payment_service.serviceimpl;

import com.sevketbuyukdemir.payment_service.constant.PaymentAttemptStatus;
import com.sevketbuyukdemir.payment_service.constant.PaymentEventType;
import com.sevketbuyukdemir.payment_service.constant.PaymentStatus;
import com.sevketbuyukdemir.payment_service.dto.PaymentAttemptDTO;
import com.sevketbuyukdemir.payment_service.entity.Payment;
import com.sevketbuyukdemir.payment_service.entity.PaymentAttempt;
import com.sevketbuyukdemir.payment_service.event.PaymentApplicationEvent;
import com.sevketbuyukdemir.payment_service.repository.PaymentAttemptRepository;
import com.sevketbuyukdemir.payment_service.repository.PaymentRepository;
import com.sevketbuyukdemir.payment_service.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final PaymentRepository paymentRepository;
    private final PaymentAttemptRepository attemptRepository;

    @Override
    @Transactional
    public void processPayment(PaymentAttemptDTO paymentDTO) {
        Optional<PaymentAttempt> existingAttempt = attemptRepository.findByIdempotencyKey(paymentDTO.getIdempotencyKey());
        if (existingAttempt.isPresent()) return;
        Payment payment = paymentRepository.findByOrderId(paymentDTO.getOrderId()).orElseGet(() -> paymentRepository.save(new Payment(paymentDTO)));
        PaymentAttempt attempt = new PaymentAttempt(payment, paymentDTO.getIdempotencyKey());
        attempt = attemptRepository.save(attempt);
        if (Math.random() < 0.5) {
            attempt.setStatus(PaymentAttemptStatus.SUCCEEDED);
            attemptRepository.save(attempt);
            payment.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(payment);
            PaymentApplicationEvent event = new PaymentApplicationEvent(this, PaymentEventType.SUCCESS, payment);
            applicationEventPublisher.publishEvent(event);
        } else {
            attempt.setStatus(PaymentAttemptStatus.FAILED);
            attempt.setFailureReason("Something went wrong");
            attemptRepository.save(attempt);
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            PaymentApplicationEvent event = new PaymentApplicationEvent(this, PaymentEventType.FAILED, payment);
            applicationEventPublisher.publishEvent(event);
        }
    }
}
