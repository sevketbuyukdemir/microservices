package com.sevketbuyukdemir.inventory_service.repository;

import com.sevketbuyukdemir.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    @Modifying
    @Query("UPDATE inventory i SET i.availableStock = i.availableStock + :quantity, i.updatedAt = CURRENT_TIMESTAMP WHERE i.id = :productId")
    int addStock(@Param("productId") long productId, @Param("quantity") int quantity);

    @Modifying
    @Query("UPDATE inventory i SET i.availableStock = i.availableStock - :quantity, i.updatedAt = CURRENT_TIMESTAMP WHERE i.id = :productId AND i.availableStock >= :quantity")
    int removeStock(@Param("productId") long productId, @Param("quantity") int quantity);

    @Modifying
    @Query("UPDATE inventory i SET i.availableStock = :availableStock, i.reservedStock = :reservedStock, i.updatedAt = CURRENT_TIMESTAMP WHERE i.id = :productId")
    int adjustStocks(@Param("productId") long productId,
                     @Param("availableStock") int availableStock,
                     @Param("reservedStock") int reservedStock);
}
