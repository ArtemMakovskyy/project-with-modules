package com.store.rabbitconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ConsumerConfig {

    @Bean
    public Queue consumerDevJavaQueue() {
        return new Queue("q.dev.java", false);
    }

    @Bean
    public Queue consumerDevCSQueue() {
        return new Queue("q.dev.cs", false);
    }

    @Bean
    public Queue consumerQaQueue() {
        return new Queue("q.qa", false);
    }

    @Bean
    public DirectExchange consumerDirectExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public TopicExchange consumerTopicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Queue consumerDevQueue() {
        return new Queue("q.dev", false);
    }

    @Bean
    public Binding consumerDevBinding(Queue consumerDevQueue, TopicExchange consumerTopicExchange) {
        return BindingBuilder.bind(consumerDevQueue)
                .to(consumerTopicExchange)
                .with("q.dev.#");
    }

    @Bean
    public Binding consumerDevJavaBinding(Queue consumerDevJavaQueue, DirectExchange consumerDirectExchange) {
        return BindingBuilder.bind(consumerDevJavaQueue)
                .to(consumerDirectExchange)
                .with("dev-java");
    }

    @Bean
    public Binding consumerDevCSBinding(Queue consumerDevCSQueue, DirectExchange consumerDirectExchange) {
        return BindingBuilder.bind(consumerDevCSQueue)
                .to(consumerDirectExchange)
                .with("dev-cs");
    }

    @Bean
    public Binding consumerQaBinding(Queue consumerQaQueue, DirectExchange consumerDirectExchange) {
        return BindingBuilder.bind(consumerQaQueue)
                .to(consumerDirectExchange)
                .with("qa");
    }
}
