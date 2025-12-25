package com.store.rabbitproducer.controller;

import com.store.rabbitproducer.dto.PublishRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbit")
@RequiredArgsConstructor
public class RabbitController {

    private final RabbitTemplate rabbitTemplate;

    // =========================
    // DIRECT / TOPIC
    // =========================
    @PostMapping("/publish")
    public void publish(@RequestBody PublishRequest request) {
        rabbitTemplate.convertAndSend(
                request.exchange(),
                request.routingKey(),
                request.message()
        );
    }

    // =========================
    // HEADERS — Java DEV (AND)
    // lang=java AND level=dev
    // =========================
    @PostMapping("/publish/header/java-dev")
    public void publishJavaDev(@RequestBody PublishRequest request) {
        rabbitTemplate.convertAndSend(
                "headers-exchange",
                "",
                request.message(),
                msg -> {
                    msg.getMessageProperties().getHeaders().put("lang", "java");
                    msg.getMessageProperties().getHeaders().put("level", "dev");
                    return msg;
                }
        );
    }

    // =========================
    // HEADERS — QA (OR)
    // role=qa
    // =========================
    @PostMapping("/publish/header/qa-role")
    public void publishQaRole(@RequestBody PublishRequest request) {
        rabbitTemplate.convertAndSend(
                "headers-exchange",
                "",
                request.message(),
                msg -> {
                    msg.getMessageProperties().getHeaders().put("role", "qa");
                    return msg;
                }
        );
    }

    // =========================
    // HEADERS — QA (OR)
    // department=testing
    // =========================
    @PostMapping("/publish/header/qa-department")
    public void publishQaDepartment(@RequestBody PublishRequest request) {
        rabbitTemplate.convertAndSend(
                "headers-exchange",
                "",
                request.message(),
                msg -> {
                    msg.getMessageProperties().getHeaders().put("department", "testing");
                    return msg;
                }
        );
    }
}
