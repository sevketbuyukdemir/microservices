package com.sevketbuyukdemir.inventory_service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sevketbuyukdemir.inventory_service.constant.InventoryEventType;
import com.sevketbuyukdemir.inventory_service.constant.ProductEventType;
import com.sevketbuyukdemir.inventory_service.dto.KafkaProductDTO;
import com.sevketbuyukdemir.inventory_service.entity.Inventory;
import com.sevketbuyukdemir.inventory_service.event.InventoryEvent;
import com.sevketbuyukdemir.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductConsumer {
    private final ApplicationEventPublisher eventPublisher;
    private final InventoryRepository inventoryRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "product-topic")
    public void consume(@Payload String message) {
        try {
            KafkaProductDTO dto = objectMapper.readValue(message, KafkaProductDTO.class);
            if (ProductEventType.CREATED.equals(dto.getEventType())) {
                Inventory inventory = new Inventory();
                inventory.setProductId(dto.getProductId());
                Inventory createdInventory = inventoryRepository.save(inventory);
                InventoryEvent event = new InventoryEvent(this, InventoryEventType.INVENTORY_CREATED, createdInventory);
                eventPublisher.publishEvent(event);
            } else if (ProductEventType.DELETED.equals(dto.getEventType())) {
                Inventory inventory = inventoryRepository.findByProductId(dto.getProductId()).get();
                inventoryRepository.delete(inventory);
                InventoryEvent event = new InventoryEvent(this, InventoryEventType.INVENTORY_DEACTIVATED, inventory);
                eventPublisher.publishEvent(event);
            }
        } catch (Exception e) {

        }
    }
}
