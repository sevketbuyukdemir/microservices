package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.InventoryEventType;
import lombok.Data;

@Data
public class KafkaInventoryDTO {
    private InventoryEventType eventType;
    private InventoryDTO inventory;

    public KafkaInventoryDTO(InventoryEventType eventType, InventoryDTO inventory) {
        this.eventType = eventType;
        this.inventory = inventory;
    }
}
