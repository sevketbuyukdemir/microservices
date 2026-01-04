package com.sevketbuyukdemir.payment_service.repository;

import com.sevketbuyukdemir.payment_service.entity.PaymentAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentAttemptRepository extends JpaRepository<PaymentAttempt, Long> {
    Optional<PaymentAttempt> findByIdempotencyKey(String idempotencyKey);
}
