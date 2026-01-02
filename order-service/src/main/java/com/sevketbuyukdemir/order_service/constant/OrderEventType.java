package com.sevketbuyukdemir.order_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum OrderEventType {
    ORDER_CREATED,
    ORDER_CANCELLED,
    ORDER_COMPLETED,
    ORDER_FAILED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
