package com.sevketbuyukdemir.product_service.serviceimpl;

import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import com.sevketbuyukdemir.product_service.entity.Product;
import com.sevketbuyukdemir.product_service.repository.ProductAttributeRepository;
import com.sevketbuyukdemir.product_service.repository.ProductCategoryRepository;
import com.sevketbuyukdemir.product_service.repository.ProductRepository;
import com.sevketbuyukdemir.product_service.request.CreateProductRequest;
import com.sevketbuyukdemir.product_service.request.UpdateProductRequest;
import com.sevketbuyukdemir.product_service.response.*;
import com.sevketbuyukdemir.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductAttributeRepository productAttributeRepository;

    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = new Product(request.getProduct());
        Product createdProduct = productRepository.save(product);
        cleanupUnmapped();
        return new CreateProductResponse(new ProductDTO(createdProduct));
    }

    @Override
    public GetProductResponse readProduct(String name) {
        Product product = productRepository.selectByName(name);
        return new GetProductResponse(new ProductDTO(product));
    }

    @Override
    public GetProductsResponse readProducts() {
        List<Product> products = productRepository.selectAll();
        return new GetProductsResponse(products.stream().map(ProductDTO::new).collect(Collectors.toList()));
    }

    @Override
    public UpdateProductResponse updateProduct(String name, UpdateProductRequest request) {
        Product product = productRepository.selectByName(name);
        product.updateProduct(request);
        Product updatedProduct = productRepository.save(product);
        cleanupUnmapped();
        return new UpdateProductResponse(new ProductDTO(updatedProduct));
    }

    @Override
    public DeleteProductResponse deleteProduct(String name) {
        Product product = productRepository.selectByName(name);
        productRepository.delete(product);
        cleanupUnmapped();
        return new DeleteProductResponse();
    }

    private void cleanupUnmapped() {
        productCategoryRepository.deleteUnmappedCategories();
        productAttributeRepository.deleteUnmappedAttributes();
    }
}
