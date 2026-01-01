package com.sevketbuyukdemir.inventory_service.kafka;


import com.sevketbuyukdemir.inventory_service.dto.KafkaInventoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryProducer {
    private final KafkaTemplate<String, KafkaInventoryDTO> kafkaTemplate;

    public void send(KafkaInventoryDTO dto) {
        kafkaTemplate.send("inventory-topic", String.valueOf(dto.getProductId()), dto);
    }
}
