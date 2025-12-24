package com.store.rabbitmq.producer;

import com.store.rabbitmq.config.RabbitMQConfig;
import com.store.rabbitmq.entity.Order;
import com.store.rabbitmq.entity.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RMQProducer {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/order")
    public OrderDTO placeOrder(@RequestBody Order order) {

        OrderDTO orderDTO = new OrderDTO(
                order, "Order Placed", "Producer: Your order has been placed.");

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, orderDTO);

        return orderDTO;
    }
}