package com.sevketbuyukdemir.inventory_service.constant;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Locale;

public enum InventoryEventType {
    INVENTORY_CREATED,
    INVENTORY_DEACTIVATED,
    INVENTORY_ADJUSTED;

    @JsonValue
    public String toLower() {
        return this.name().toLowerCase(Locale.ENGLISH);
    }
}
