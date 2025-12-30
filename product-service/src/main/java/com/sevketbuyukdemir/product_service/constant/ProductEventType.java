package com.sevketbuyukdemir.product_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum ProductEventType {
    CREATED,
    UPDATED,
    DELETED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
