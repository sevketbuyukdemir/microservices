package com.sevketbuyukdemir.order_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum ReservationEventType {
    STOCK_ADJUSTED,
    STOCK_RESERVED,
    STOCK_RESERVATION_FAILED,
    STOCK_RELEASED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
