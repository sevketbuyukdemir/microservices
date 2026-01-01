package com.sevketbuyukdemir.inventory_service.service;

import com.sevketbuyukdemir.inventory_service.request.AddStockRequest;
import com.sevketbuyukdemir.inventory_service.request.AdjustStockRequest;
import com.sevketbuyukdemir.inventory_service.request.RemoveStockRequest;
import com.sevketbuyukdemir.inventory_service.response.AddStockResponse;
import com.sevketbuyukdemir.inventory_service.response.AdjustStockResponse;
import com.sevketbuyukdemir.inventory_service.response.RemoveStockResponse;

public interface InventoryService {
    AddStockResponse addStock(long productId, AddStockRequest request);
    RemoveStockResponse removeStock(long productId, RemoveStockRequest request);
    AdjustStockResponse adjustStocks(long productId, AdjustStockRequest request);
}
