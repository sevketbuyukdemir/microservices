package com.sevketbuyukdemir.inventory_service.event;

import com.sevketbuyukdemir.inventory_service.constant.InventoryEventType;
import com.sevketbuyukdemir.inventory_service.entity.Inventory;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class InventoryEvent extends ApplicationEvent {
    private final InventoryEventType type;
    private final Inventory inventory;

    public InventoryEvent(Object source, InventoryEventType type, Inventory inventory) {
        super(source);
        this.type = type;
        this.inventory = inventory;
    }
}
