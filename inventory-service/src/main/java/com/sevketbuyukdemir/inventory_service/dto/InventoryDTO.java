package com.sevketbuyukdemir.inventory_service.dto;

import com.sevketbuyukdemir.inventory_service.entity.Inventory;
import lombok.Data;

@Data
public class InventoryDTO {
    private long productId;
    private int availableStock;
    private int reservedStock;

    public InventoryDTO() {
    }

    public InventoryDTO(Inventory inventory) {
        this.productId = inventory.getProductId();
        this.availableStock = inventory.getAvailableStock();
        this.reservedStock = inventory.getReservedStock();
    }
}
