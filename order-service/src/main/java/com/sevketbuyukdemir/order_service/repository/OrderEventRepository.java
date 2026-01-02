package com.sevketbuyukdemir.order_service.repository;


import com.sevketbuyukdemir.order_service.entity.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
