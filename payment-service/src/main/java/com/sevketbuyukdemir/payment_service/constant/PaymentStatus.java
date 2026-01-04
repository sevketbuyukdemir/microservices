package com.sevketbuyukdemir.payment_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum PaymentStatus {
    PENDING,
    PROCESSING,
    SUCCESS,
    FAILED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
