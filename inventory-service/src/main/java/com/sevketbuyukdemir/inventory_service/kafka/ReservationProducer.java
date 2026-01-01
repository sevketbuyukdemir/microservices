package com.sevketbuyukdemir.inventory_service.kafka;

import com.sevketbuyukdemir.inventory_service.dto.KafkaReservationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReservationProducer {
    private final KafkaTemplate<String, KafkaReservationDTO> kafkaTemplate;

    public void send(KafkaReservationDTO dto) {
        kafkaTemplate.send("reservation-topic", String.valueOf(dto.getReservation().getOrderId()), dto);
    }

}