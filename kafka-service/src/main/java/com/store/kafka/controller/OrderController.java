package com.store.kafka.controller;

import com.store.kafka.dto.OrderEventDto;
import com.store.kafka.entity.OrderEvent;
import com.store.kafka.service.KafkaProducer;
import com.store.kafka.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final KafkaProducer kafkaProducer;
    private final OrderService orderService;

    @PostMapping("/standard")
    public ResponseEntity<String> sendStandardOrder(@RequestBody OrderEventDto orderEventDto) {
        kafkaProducer.sendStandard(orderEventDto.orderId(), orderEventDto);
        return ResponseEntity.ok("Order sent via standard producer");
    }

    @PostMapping("/transactional")
    public ResponseEntity<String> sendTransactionalOrder(@RequestBody OrderEventDto orderEventDto) {
        kafkaProducer.sendTransactional(orderEventDto.orderId(), orderEventDto);
        return ResponseEntity.ok("Order sent via transactional producer");
    }

    @PostMapping("/high-throughput")
    public ResponseEntity<String> sendHighThroughputOrder(@RequestBody OrderEventDto orderEventDto) {
        kafkaProducer.sendHighThroughput(orderEventDto.orderId(), orderEventDto);
        return ResponseEntity.ok("Order sent via high throughput producer");
    }

    @PostMapping("/low-latency")
    public ResponseEntity<String> sendLowLatencyOrder(@RequestBody OrderEventDto orderEventDto) {
        kafkaProducer.sendLowLatency(orderEventDto.orderId(), orderEventDto);
        return ResponseEntity.ok("Order sent via low latency producer");
    }

    @PostMapping("/reliable")
    public ResponseEntity<String> sendReliableOrder(@RequestBody OrderEventDto orderEventDto) {
        kafkaProducer.sendReliable(orderEventDto.orderId(), orderEventDto);
        return ResponseEntity.ok("Order sent via reliable producer");
    }

    @GetMapping
    public ResponseEntity<List<OrderEvent>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderEvent>> getOrdersByOrderId(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(orderService.getOrdersByOrderId(orderId));
    }
}