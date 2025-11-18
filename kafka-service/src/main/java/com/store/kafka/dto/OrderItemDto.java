package com.store.kafka.dto;

import java.math.BigDecimal;

public record OrderItemDto(
        String productId,
        String productName,
        Integer quantity,
        BigDecimal price
) {}