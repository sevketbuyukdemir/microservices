package com.sevketbuyukdemir.product_service.repository;

import com.sevketbuyukdemir.product_service.entity.ProductAttribute;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
    @Transactional
    @Modifying
    @Query(value = """
            DELETE FROM product_attributes
            WHERE id NOT IN (
                SELECT DISTINCT attribute_id
                FROM product_attribute_map
            )
            """, nativeQuery = true)
    void deleteUnmappedAttributes();
}
