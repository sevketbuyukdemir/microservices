package com.sevketbuyukdemir.payment_service.util;

import java.util.UUID;

public class IdempotencyKeyGenerator {
    public static String generateKey() {
        return UUID.randomUUID().toString();
    }
}
