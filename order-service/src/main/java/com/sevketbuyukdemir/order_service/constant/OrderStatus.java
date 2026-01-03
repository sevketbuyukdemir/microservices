package com.sevketbuyukdemir.order_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum OrderStatus {
    CREATED,
    INVENTORY_PENDING,
    INVENTORY_REJECTED,
    PAYMENT_PENDING,
    PAYMENT_REJECTED,
    COMPLETED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
