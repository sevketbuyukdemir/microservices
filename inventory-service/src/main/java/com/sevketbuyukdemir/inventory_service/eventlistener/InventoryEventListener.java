package com.sevketbuyukdemir.inventory_service.eventlistener;

import com.sevketbuyukdemir.inventory_service.constant.InventoryEventType;
import com.sevketbuyukdemir.inventory_service.dto.InventoryDTO;
import com.sevketbuyukdemir.inventory_service.dto.KafkaInventoryDTO;
import com.sevketbuyukdemir.inventory_service.entity.Inventory;
import com.sevketbuyukdemir.inventory_service.event.InventoryEvent;
import com.sevketbuyukdemir.inventory_service.kafka.InventoryProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventListener {
    private final InventoryProducer inventoryProducer;

    @EventListener
    public void handleInventoryEvent(InventoryEvent inventoryEvent) {
        InventoryEventType type = inventoryEvent.getType();
        Inventory inventory = inventoryEvent.getInventory();
        InventoryDTO inventoryDTO = new InventoryDTO(inventory);
        KafkaInventoryDTO dto = new KafkaInventoryDTO(type, inventoryDTO);
        inventoryProducer.send(dto);
    }
}
