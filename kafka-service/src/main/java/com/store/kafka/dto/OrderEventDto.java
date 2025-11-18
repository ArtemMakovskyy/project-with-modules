package com.store.kafka.dto;

import com.store.kafka.entity.OrderEventType;
import com.store.kafka.entity.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderEventDto(
        String orderId,
        String userId,
        OrderEventType eventType,
        OrderStatus orderStatus,
        BigDecimal totalAmount,
        String currency,
        List<OrderItemDto> items,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String paymentMethod,
        String shippingAddress
) {}