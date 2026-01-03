package com.sevketbuyukdemir.inventory_service.serviceimpl;

import com.sevketbuyukdemir.inventory_service.constant.ReservationStatus;
import com.sevketbuyukdemir.inventory_service.dto.OrderItemDTO;
import com.sevketbuyukdemir.inventory_service.entity.Reservation;
import com.sevketbuyukdemir.inventory_service.repository.InventoryRepository;
import com.sevketbuyukdemir.inventory_service.repository.ReservationRepository;
import com.sevketbuyukdemir.inventory_service.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final InventoryRepository inventoryRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public boolean reserveStock(long orderId, List<OrderItemDTO> items) {
        for (OrderItemDTO item : items) {
            int updated = inventoryRepository.reserveStock(item.getProductId(), item.getQuantity());
            if (updated == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
            Reservation reservation = new Reservation();
            reservation.setStatus(ReservationStatus.RESERVED);
            reservation.setOrderId(orderId);
            reservation.setProductId(item.getProductId());
            reservation.setQuantity(item.getQuantity());
            reservationRepository.save(reservation);
        }
        return true;
    }

    @Override
    @Transactional
    public void removeReserved(long orderId, List<OrderItemDTO> items) {
        for (OrderItemDTO item : items) {
            int updated = inventoryRepository.releaseReservedStock(item.getProductId(), item.getQuantity());
            if (updated == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            List<Reservation> reservations = reservationRepository.findByOrderIdAndProductIdAndStatus(orderId, item.getProductId(), ReservationStatus.RESERVED);
            if (reservations.isEmpty()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            for (Reservation reservation : reservations) {
                reservation.setStatus(ReservationStatus.SOLD);
                reservationRepository.save(reservation);
            }
        }
    }

    @Override
    @Transactional
    public void releaseStock(long orderId, List<OrderItemDTO> items) {
        for (OrderItemDTO item : items) {
            int updated = inventoryRepository.undoReserveStock(item.getProductId(), item.getQuantity());
            if (updated == 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            List<Reservation> reservations = reservationRepository.findByOrderIdAndProductIdAndStatus(orderId, item.getProductId(), ReservationStatus.RESERVED);
            if (reservations.isEmpty()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            for (Reservation reservation : reservations) {
                reservation.setStatus(ReservationStatus.RELEASED);
                reservationRepository.save(reservation);
            }
        }
    }
}
