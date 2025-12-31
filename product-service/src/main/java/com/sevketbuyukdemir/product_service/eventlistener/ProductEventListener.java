package com.sevketbuyukdemir.product_service.eventlistener;


import com.sevketbuyukdemir.product_service.constant.ProductEventType;
import com.sevketbuyukdemir.product_service.dto.KafkaProductDTO;
import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import com.sevketbuyukdemir.product_service.entity.Product;
import com.sevketbuyukdemir.product_service.event.ProductEvent;
import com.sevketbuyukdemir.product_service.kafka.ProductProducer;
import com.sevketbuyukdemir.product_service.redisentity.ProductCache;
import com.sevketbuyukdemir.product_service.redisrepository.ProductRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductEventListener {
    private final ProductRedisRepository productRedisRepository;
    private final ProductProducer productProducer;

    @EventListener
    public void handleProductEvents(ProductEvent event) {
        ProductEventType type = event.getType();
        Product product = event.getProduct();
        ProductDTO productDTO = new ProductDTO(product);
        KafkaProductDTO dto = new KafkaProductDTO(type, productDTO);
        productProducer.send(dto);
        if (type == ProductEventType.CREATED || type == ProductEventType.UPDATED) {
            ProductCache productCache = new ProductCache(productDTO);
            productRedisRepository.save(productCache);
        } else if (type == ProductEventType.DELETED) {
            productRedisRepository.deleteById(product.getName());
        }
    }
}