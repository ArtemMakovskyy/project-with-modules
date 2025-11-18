package com.store.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

    @GetMapping("/api/kafka/test")
    public String test(){
        System.out.println("kafka test");
        return "test KafkaTestController";
    }
}
