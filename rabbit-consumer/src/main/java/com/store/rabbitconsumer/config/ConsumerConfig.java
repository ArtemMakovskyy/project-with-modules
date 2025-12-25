package com.store.rabbitconsumer.config;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ConsumerConfig {

    // =========================================================
    // 1️⃣ HEADERS EXCHANGE CONFIGURATION
    // =========================================================

    // ---------- Exchange ----------
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headers-exchange", true, false);
    }

    // ---------- Queues ----------
    @Bean
    public Queue headerJavaQueue() {
        return new Queue("q.headers.java", false);
    }

    @Bean
    public Queue headerQaQueue() {
        return new Queue("q.headers.qa", false);
    }

    // ---------- Bindings ----------
    // AND: lang=java AND level=dev
    @Bean
    public Binding javaHeadersBinding(
            Queue headerJavaQueue,
            HeadersExchange headersExchange
    ) {
        return BindingBuilder.bind(headerJavaQueue)
                .to(headersExchange)
                .whereAll(Map.of(
                        "lang", "java",
                        "level", "dev"
                ))
                .match();
    }

    // OR: role=qa OR department=testing
    @Bean
    public Binding qaHeadersBinding(
            Queue headerQaQueue,
            HeadersExchange headersExchange
    ) {
        return BindingBuilder.bind(headerQaQueue)
                .to(headersExchange)
                .whereAny(Map.of(
                        "role", "qa",
                        "department", "testing"
                ))
                .match();
    }

    // =========================================================
    // 2️⃣ DIRECT EXCHANGE CONFIGURATION
    // =========================================================

    // ---------- Exchange ----------
    @Bean
    public DirectExchange consumerDirectExchange() {
        return new DirectExchange("direct-exchange");
    }

    // ---------- Queues ----------
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

    // ---------- Bindings ----------
    @Bean
    public Binding consumerDevJavaBinding(
            Queue consumerDevJavaQueue,
            DirectExchange consumerDirectExchange
    ) {
        return BindingBuilder.bind(consumerDevJavaQueue)
                .to(consumerDirectExchange)
                .with("dev-java");
    }

    @Bean
    public Binding consumerDevCSBinding(
            Queue consumerDevCSQueue,
            DirectExchange consumerDirectExchange
    ) {
        return BindingBuilder.bind(consumerDevCSQueue)
                .to(consumerDirectExchange)
                .with("dev-cs");
    }

    @Bean
    public Binding consumerQaBinding(
            Queue consumerQaQueue,
            DirectExchange consumerDirectExchange
    ) {
        return BindingBuilder.bind(consumerQaQueue)
                .to(consumerDirectExchange)
                .with("qa");
    }

    // =========================================================
    // 3️⃣ TOPIC EXCHANGE CONFIGURATION
    // =========================================================

    // ---------- Exchange ----------
    @Bean
    public TopicExchange consumerTopicExchange() {
        return new TopicExchange("topic-exchange");
    }

    // ---------- Queue ----------
    @Bean
    public Queue consumerDevQueue() {
        return new Queue("q.dev", false);
    }

    // ---------- Binding ----------
    @Bean
    public Binding consumerDevBinding(
            Queue consumerDevQueue,
            TopicExchange consumerTopicExchange
    ) {
        return BindingBuilder.bind(consumerDevQueue)
                .to(consumerTopicExchange)
                .with("q.dev.#");
    }
}
