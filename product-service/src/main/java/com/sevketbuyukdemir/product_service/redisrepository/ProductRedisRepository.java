package com.sevketbuyukdemir.product_service.redisrepository;

import com.sevketbuyukdemir.product_service.redisentity.ProductCache;
import org.springframework.data.repository.CrudRepository;

public interface ProductRedisRepository extends CrudRepository<ProductCache, String> {
}
