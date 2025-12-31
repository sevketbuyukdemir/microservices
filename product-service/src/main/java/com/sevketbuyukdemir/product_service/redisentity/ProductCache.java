package com.sevketbuyukdemir.product_service.redisentity;

import com.sevketbuyukdemir.product_service.dto.ProductDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "content", timeToLive = 86400)
@Data
public class ProductCache {
    @Id
    String name;
    private ProductDTO product;

    public ProductCache(ProductDTO product) {
        this.name = product.getName();
        this.product = product;
    }
}
