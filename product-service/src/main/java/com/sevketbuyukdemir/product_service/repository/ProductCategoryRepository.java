package com.sevketbuyukdemir.product_service.repository;

import com.sevketbuyukdemir.product_service.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Transactional
    @Modifying
    @Query(value = """
            DELETE FROM product_categories
            WHERE id NOT IN (
                SELECT DISTINCT category_id
                FROM product_category_map
            )
            """, nativeQuery = true)
    void deleteUnmappedCategories();
}
