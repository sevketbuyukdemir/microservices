package com.sevketbuyukdemir.product_service.events;

import com.sevketbuyukdemir.product_service.constant.ProductEventType;
import com.sevketbuyukdemir.product_service.entity.Product;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ProductEvent extends ApplicationEvent {
    private final ProductEventType type;
    private final Product product;

    public ProductEvent(Object source, ProductEventType type, Product product) {
        super(source);
        this.type = type;
        this.product = product;
    }
}
