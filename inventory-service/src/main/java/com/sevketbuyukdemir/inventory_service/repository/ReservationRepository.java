package com.sevketbuyukdemir.inventory_service.repository;

import com.sevketbuyukdemir.inventory_service.constant.ReservationStatus;
import com.sevketbuyukdemir.inventory_service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByOrderIdAndProductIdAndStatus(long orderId, long productId, ReservationStatus status);
}
