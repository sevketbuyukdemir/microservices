package com.sevketbuyukdemir.product_service.eventlistener;


import com.sevketbuyukdemir.product_service.constant.ProductEventType;
import com.sevketbuyukdemir.product_service.entity.Product;
import com.sevketbuyukdemir.product_service.events.ProductEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class ProductEventListener {

    @EventListener
    public void handleProductCreatedEvent(ProductEvent event) {
        ProductEventType type = event.getType();
        Product product = event.getProduct();
        if (type == ProductEventType.CREATED) {
            System.out.println("Product " + product.getName() + " has been created");
        } else if (type == ProductEventType.UPDATED) {
            System.out.println("Product " + product.getName() + " has been updated");
        } else if (type == ProductEventType.DELETED) {
            System.out.println("Product " + product.getName() + " has been deleted");
        }
    }
}