package com.store.rabbitproducer.dto;

public record PublishRequest (
        String message,
        String exchange,
        String routingKey
){
}
