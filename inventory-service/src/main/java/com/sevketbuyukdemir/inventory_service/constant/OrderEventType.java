package com.sevketbuyukdemir.inventory_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum OrderEventType {
    ORDER_CREATED,
    ORDER_INVENTORY_PENDING,
    ORDER_INVENTORY_REJECTED,
    ORDER_PAYMENT_PENDING,
    ORDER_PAYMENT_REJECTED,
    ORDER_COMPLETED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
