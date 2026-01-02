package com.sevketbuyukdemir.order_service.kafka;

import com.sevketbuyukdemir.order_service.dto.KafkaOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, KafkaOrderDTO> kafkaTemplate;

    public void send(KafkaOrderDTO dto) {
        kafkaTemplate.send("order-topic", String.valueOf(dto.getId()), dto);
    }
}
