package com.sevketbuyukdemir.inventory_service.request;

import lombok.Data;

@Data
public class AdjustStockRequest {
    private int availableStock;
    private int reservedStock;
}
