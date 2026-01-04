package com.sevketbuyukdemir.payment_service.kafka;

import com.sevketbuyukdemir.payment_service.dto.KafkaPaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final KafkaTemplate<String, KafkaPaymentDTO> kafkaTemplate;

    public void send(KafkaPaymentDTO dto) {
        kafkaTemplate.send("payment-topic", String.valueOf(dto.getOrderId()), dto);
    }
}
