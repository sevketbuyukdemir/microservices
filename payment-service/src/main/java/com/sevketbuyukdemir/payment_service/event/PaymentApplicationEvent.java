package com.sevketbuyukdemir.payment_service.event;

import com.sevketbuyukdemir.payment_service.constant.PaymentEventType;
import com.sevketbuyukdemir.payment_service.entity.Payment;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class PaymentApplicationEvent extends ApplicationEvent {
    private final PaymentEventType type;
    private final Payment payment;

    public PaymentApplicationEvent(Object source, PaymentEventType type, Payment payment) {
        super(source);
        this.type = type;
        this.payment = payment;
    }
}
