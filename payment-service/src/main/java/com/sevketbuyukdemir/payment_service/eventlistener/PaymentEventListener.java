package com.sevketbuyukdemir.payment_service.eventlistener;

import com.sevketbuyukdemir.payment_service.dto.KafkaPaymentDTO;
import com.sevketbuyukdemir.payment_service.dto.PaymentRequestDTO;
import com.sevketbuyukdemir.payment_service.event.PaymentApplicationEvent;
import com.sevketbuyukdemir.payment_service.kafka.PaymentProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventListener {

    private final PaymentProducer paymentProducer;

    @EventListener
    public void handlePaymentEvent(PaymentApplicationEvent event) {
        KafkaPaymentDTO dto = new KafkaPaymentDTO(event.getType(), new PaymentRequestDTO(event.getPayment()));
        paymentProducer.send(dto);
    }
}
