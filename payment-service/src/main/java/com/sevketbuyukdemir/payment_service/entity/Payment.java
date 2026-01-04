package com.sevketbuyukdemir.payment_service.entity;

import com.sevketbuyukdemir.payment_service.constant.PaymentStatus;
import com.sevketbuyukdemir.payment_service.dto.PaymentAttemptDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "payments",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_payment_order", columnNames = "order_id")
        }
)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private PaymentStatus status;

    @Column(nullable = false, length = 64)
    private String provider;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    private List<PaymentAttempt> attempts;

    public Payment() {
    }

    public Payment(PaymentAttemptDTO dto) {
        this.orderId = dto.getOrderId();
        this.userEmail = dto.getUserEmail();
        this.amount = dto.getAmount();
        this.currency = dto.getCurrency();
        this.provider = dto.getProvider();
        this.status = PaymentStatus.PENDING;
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
