package com.store.rabbitconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Listener {

    @RabbitListener(queues = "q.dev.java")
    public void listenJavaQueue(String message) {
        log.info("Received message from queue direct '{}' - JAVA", message);
    }

    @RabbitListener(queues = "q.dev.cs")
    public void listenCSQueue(String message) {
        log.info("Received message from queue direct '{}' - CS", message);
    }

    @RabbitListener(queues = "q.qa")
    public void listenQAQueue(String message) {
        log.info("Received message from queue '{}' - QA", message);
    }

    @RabbitListener(queues = "q.dev")
    public void listenDevQueue(String message) {
        log.info("Received message from queue '{}' - DEV", message);
    }

    // HEADERS

    @RabbitListener(queues = "q.headers.java")
    public void listenHeadersJava(String message) {
        log.info("HEADERS JAVA received: {}", message);
    }

    @RabbitListener(queues = "q.headers.qa")
    public void listenHeadersQa(String message) {
        log.info("HEADERS QA received: {}", message);
    }

}
