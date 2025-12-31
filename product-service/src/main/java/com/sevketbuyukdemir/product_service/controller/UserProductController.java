package com.sevketbuyukdemir.product_service.controller;

import com.sevketbuyukdemir.product_service.response.BaseResponse;
import com.sevketbuyukdemir.product_service.response.GetProductResponse;
import com.sevketbuyukdemir.product_service.response.GetProductsResponse;
import com.sevketbuyukdemir.product_service.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserProductController {
    private final ProductService productService;

    public UserProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{name}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BaseResponse> getProduct(Authentication authentication, HttpServletRequest request, @PathVariable("name") String name) {
        GetProductResponse response = productService.readProduct(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BaseResponse> getProducts(Authentication authentication, HttpServletRequest request) {
        GetProductsResponse response = productService.readProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
