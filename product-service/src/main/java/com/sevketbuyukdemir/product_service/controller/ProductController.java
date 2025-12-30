package com.sevketbuyukdemir.product_service.controller;

import com.sevketbuyukdemir.product_service.request.CreateProductRequest;
import com.sevketbuyukdemir.product_service.request.UpdateProductRequest;
import com.sevketbuyukdemir.product_service.response.*;
import com.sevketbuyukdemir.product_service.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> createProduct(Authentication authentication, HttpServletRequest request, @RequestBody CreateProductRequest createProductRequest) {
        CreateProductResponse response = productService.createProduct(createProductRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<BaseResponse> getProduct(Authentication authentication, HttpServletRequest request, @PathVariable("name") String name) {
        GetProductResponse response = productService.readProduct(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getProducts(Authentication authentication, HttpServletRequest request) {
        GetProductsResponse response = productService.readProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updateProduct(Authentication authentication, HttpServletRequest request, @PathVariable("name") String name, @RequestBody UpdateProductRequest updateProductRequest) {
        UpdateProductResponse response = productService.updateProduct(name, updateProductRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<BaseResponse> deleteProduct(Authentication authentication, HttpServletRequest request, @PathVariable("name") String name) {
        DeleteProductResponse response = productService.deleteProduct(name);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
