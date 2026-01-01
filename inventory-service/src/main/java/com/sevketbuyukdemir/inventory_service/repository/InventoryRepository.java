package com.sevketbuyukdemir.inventory_service.repository;

import com.sevketbuyukdemir.inventory_service.entity.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<Inventory> findByProductId(long productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inventory i SET i.available_stock = i.available_stock + :quantity, i.updated_at = CURRENT_TIMESTAMP WHERE i.product_id = :productId", nativeQuery = true)
    int addStock(@Param("productId") long productId, @Param("quantity") int quantity);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inventory i SET i.available_stock = i.available_stock - :quantity, i.updated_at = CURRENT_TIMESTAMP WHERE i.product_id = :productId AND i.available_stock >= :quantity", nativeQuery = true)
    int removeStock(@Param("productId") long productId, @Param("quantity") int quantity);

    @Modifying
    @Transactional
    @Query(value = "UPDATE inventory i SET i.available_stock = :availableStock, i.reserved_stock = :reservedStock, i.updated_at = CURRENT_TIMESTAMP WHERE i.product_id = :productId", nativeQuery = true)
    int adjustStocks(@Param("productId") long productId,
                     @Param("availableStock") int availableStock,
                     @Param("reservedStock") int reservedStock);
}
