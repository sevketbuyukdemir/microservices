package com.sevketbuyukdemir.inventory_service.eventlistener;

import com.sevketbuyukdemir.inventory_service.event.InventoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventListener {
    @EventListener
    public void handleInventoryEvent(InventoryEvent inventoryEvent) {}
}
