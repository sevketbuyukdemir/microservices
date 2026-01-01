package com.sevketbuyukdemir.inventory_service.controller;

import com.sevketbuyukdemir.inventory_service.request.AddStockRequest;
import com.sevketbuyukdemir.inventory_service.request.AdjustStockRequest;
import com.sevketbuyukdemir.inventory_service.request.RemoveStockRequest;
import com.sevketbuyukdemir.inventory_service.response.AddStockResponse;
import com.sevketbuyukdemir.inventory_service.response.AdjustStockResponse;
import com.sevketbuyukdemir.inventory_service.response.BaseResponse;
import com.sevketbuyukdemir.inventory_service.response.RemoveStockResponse;
import com.sevketbuyukdemir.inventory_service.service.InventoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminInventoryController {
    private final InventoryService inventoryService;

    public AdminInventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping(path = "/{productId}/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse> addStock(Authentication authentication, HttpServletRequest request, @PathVariable("productId") String productId, @RequestBody AddStockRequest requestBody) {
        AddStockResponse response = inventoryService.addStock(Long.parseLong(productId), requestBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/{productId}/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse> removeStock(Authentication authentication, HttpServletRequest request, @PathVariable("productId") String productId, @RequestBody RemoveStockRequest requestBody) {
        RemoveStockResponse response = inventoryService.removeStock(Long.parseLong(productId), requestBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/{productId}/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResponse> adjustStocks(Authentication authentication, HttpServletRequest request, @PathVariable("productId") String productId, @RequestBody AdjustStockRequest requestBody) {
        AdjustStockResponse response = inventoryService.adjustStocks(Long.parseLong(productId), requestBody);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
