package com.sevketbuyukdemir.inventory_service.serviceimpl;

import com.sevketbuyukdemir.inventory_service.constant.InventoryEventType;
import com.sevketbuyukdemir.inventory_service.entity.Inventory;
import com.sevketbuyukdemir.inventory_service.event.InventoryEvent;
import com.sevketbuyukdemir.inventory_service.repository.InventoryRepository;
import com.sevketbuyukdemir.inventory_service.repository.ReservationRepository;
import com.sevketbuyukdemir.inventory_service.request.AddStockRequest;
import com.sevketbuyukdemir.inventory_service.request.AdjustStockRequest;
import com.sevketbuyukdemir.inventory_service.request.RemoveStockRequest;
import com.sevketbuyukdemir.inventory_service.response.AddStockResponse;
import com.sevketbuyukdemir.inventory_service.response.AdjustStockResponse;
import com.sevketbuyukdemir.inventory_service.response.RemoveStockResponse;
import com.sevketbuyukdemir.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final ApplicationEventPublisher eventPublisher;
    private final InventoryRepository inventoryRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public AddStockResponse addStock(long productId, AddStockRequest request) {
        int updated = inventoryRepository.addStock(productId, request.getQuantity());
        if (updated == 0) {
            throw new RuntimeException("Inventory not found for product: " + productId);
        }
        Inventory inventory = inventoryRepository.findById((int) productId).orElseThrow();
        publishAdjustedEvent(inventory);
        return new AddStockResponse();
    }

    @Override
    public RemoveStockResponse removeStock(long productId, RemoveStockRequest request) {
        int updated = inventoryRepository.removeStock(productId, request.getQuantity());
        if (updated == 0) {
            throw new RuntimeException("Insufficient stock or inventory not found for product: " + productId);
        }
        Inventory inventory = inventoryRepository.findById((int) productId).orElseThrow();
        publishAdjustedEvent(inventory);
        return new RemoveStockResponse();
    }

    @Override
    public AdjustStockResponse adjustStocks(long productId, AdjustStockRequest request) {
        int updated = inventoryRepository.adjustStocks(productId,
                request.getAvailableStock(),
                request.getReservedStock());
        if (updated == 0) {
            throw new RuntimeException("Inventory not found for product: " + productId);
        }
        Inventory inventory = inventoryRepository.findById((int) productId).orElseThrow();
        publishAdjustedEvent(inventory);
        return new AdjustStockResponse();
    }

    private void publishAdjustedEvent(Inventory inventory) {
        InventoryEvent event = new InventoryEvent(this, InventoryEventType.INVENTORY_ADJUSTED, inventory);
        eventPublisher.publishEvent(event);
    }
}
