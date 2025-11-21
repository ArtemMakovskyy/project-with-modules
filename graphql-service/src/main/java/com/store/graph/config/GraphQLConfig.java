package com.store.graph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import graphql.scalars.ExtendedScalars;

@Configuration
public class GraphQLConfig {

    /**
     * Bean, который регистрирует пользовательские (кастомные) скаляры GraphQL.
     *
     * В GraphQL есть ограниченный набор базовых (встроенных) типов:
     * - Int
     * - Float
     * - String
     * - Boolean
     * - ID
     *
     * Но часто нам нужны дополнительные типы, которых GraphQL "из коробки" не понимает.
     * Например:
     *   - UUID (уникальный идентификатор, часто используется в БД)
     *   - DateTime (время и дата, например ZonedDateTime в Java)
     *
     * Чтобы GraphQL мог "понять", как их сериализовать и десериализовать,
     * мы добавляем поддержку таких типов вручную через RuntimeWiringConfigurer.
     */
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {

        // RuntimeWiringConfigurer — это интерфейс, позволяющий дополнять схему GraphQL во время инициализации.
        // Внутри него мы можем добавить свои скаляры, директивы, резолверы и т.д.
        return wiringBuilder -> wiringBuilder
                // Добавляем поддержку для стандартных расширенных скаляров
                // из библиотеки graphql-java-extended-scalars.
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.UUID);
    }
}