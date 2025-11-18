package com.store.kafka.entity;

public enum OrderEventType {
        ORDER_CREATED,
        ORDER_UPDATED,
        ORDER_CANCELLED,
        ORDER_PAID,
        ORDER_SHIPPED,
        ORDER_DELIVERED,
        ORDER_RETURNED
    }