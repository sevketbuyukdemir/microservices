package com.sevketbuyukdemir.order_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum ReservationStatus {
    RESERVED,
    RELEASED,
    FAILED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}