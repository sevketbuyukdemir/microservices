package com.sevketbuyukdemir.product_service.kafka;


import com.sevketbuyukdemir.product_service.dto.KafkaProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductProducer {
    private final KafkaTemplate<String, KafkaProductDTO> kafkaTemplate;

    public void send(KafkaProductDTO dto) {
        kafkaTemplate.send("product-topic", dto.getName(), dto);
    }

}