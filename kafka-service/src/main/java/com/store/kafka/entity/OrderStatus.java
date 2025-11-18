package com.store.kafka.entity;

public enum OrderStatus {
        PENDING,
        CONFIRMED,
        PAID,
        PROCESSING,
        SHIPPED,
        DELIVERED,
        CANCELLED,
        RETURNED
    }