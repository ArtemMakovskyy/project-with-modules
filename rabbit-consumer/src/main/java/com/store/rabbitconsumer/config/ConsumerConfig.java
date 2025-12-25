package com.store.rabbitconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ConsumerConfig {

    @Bean
    public Queue devJavaQueue() {
        return new Queue("q.dev.java", false);
    }

    @Bean
    public Queue devCSQueue() {
        return new Queue("q.dev.cs", false);
    }

    @Bean
    public Queue qaQueue() {
        return new Queue("q.qa", false);
    }

    @Bean
    public Exchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public Exchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Queue devQueue() {
        return new Queue("q.dev", false);
    }

    @Bean
    public Binding devBinding(Queue devQueue, Exchange topicExchange) {
        return BindingBuilder.bind(devQueue)
                .to(topicExchange)
                .with("q.dev.#")
                .noargs();
    }

    @Bean
    public Binding devJavaBinding(Queue devJavaQueue, Exchange directExchange) {
        return BindingBuilder.bind(devJavaQueue)
                .to(directExchange)
                .with("dev-java")
                .noargs();
    }

    @Bean
    public Binding devCSBinding(Queue devCSQueue, Exchange directExchange) {
        return BindingBuilder.bind(devCSQueue)
                .to(directExchange)
                .with("dev-cs")
                .noargs();
    }

    @Bean
    public Binding qaBinding(Queue qaQueue, Exchange directExchange) {
        return BindingBuilder.bind(qaQueue)
                .to(directExchange)
                .with("qa")
                .noargs();
    }
}
