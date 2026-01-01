package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.constant.InventoryEventType;
import lombok.Data;

@Data
public class KafkaInventoryDTO {
    private InventoryEventType eventType;
    private long productId;
    private int availableStock;
    private int reservedStock;

    public KafkaInventoryDTO(InventoryEventType eventType, InventoryDTO inventory) {
        this.eventType = eventType;
        this.productId = inventory.getProductId();
        this.availableStock = inventory.getAvailableStock();
        this.reservedStock = inventory.getReservedStock();
    }
}
