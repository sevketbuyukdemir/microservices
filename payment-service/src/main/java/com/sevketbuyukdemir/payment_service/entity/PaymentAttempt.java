package com.sevketbuyukdemir.payment_service.entity;

import com.sevketbuyukdemir.payment_service.constant.PaymentAttemptStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "payment_attempts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_payment_attempt_idempotency",
                        columnNames = "idempotency_key"
                )
        },
        indexes = {
                @Index(name = "idx_payment_attempt_payment", columnList = "payment_id")
        }
)
public class PaymentAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "attempt_number", nullable = false)
    private int attemptNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private PaymentAttemptStatus status;

    @Column(name = "failure_reason", length = 255)
    private String failureReason;

    @Column(name = "idempotency_key", nullable = false, length = 128)
    private String idempotencyKey;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public PaymentAttempt() {
    }

    public PaymentAttempt(Payment payment, String idempotencyKey) {
        this.payment = payment;
        this.idempotencyKey = idempotencyKey;
        this.status = PaymentAttemptStatus.STARTED;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        this.attemptNumber = (payment.getAttempts() != null) ? payment.getAttempts().size() + 1 : 1;
    }

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }
}

