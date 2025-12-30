package com.sevketbuyukdemir.product_service.repository;

import com.sevketbuyukdemir.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products f WHERE f.name = :name", nativeQuery = true)
    Product selectByName(@Param("name") String name);

    @Query(value = "SELECT * FROM products f", nativeQuery = true)
    List<Product> selectAll();
}
