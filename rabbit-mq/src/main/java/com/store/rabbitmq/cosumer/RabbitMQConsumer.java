package com.store.rabbitmq.cosumer;

import com.store.rabbitmq.config.RabbitMQConfig;
import com.store.rabbitmq.entity.OrderDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(OrderDTO orderDTO) {
        System.out.println("Consumer received message from queue: " + orderDTO);
    }
}