package com.store.graph.config;

import com.store.graph.exception.UserNotFoundException;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Configuration
public class GraphQLErrorConfig {

    @Bean
    public DataFetcherExceptionResolver exceptionResolver() {
        return new DataFetcherExceptionResolverAdapter() {

            // Словарь соответствий исключений → GraphQL ErrorType
            private final Map<Class<? extends Throwable>, ErrorType> exceptionMappings = Map.of(
                    UserNotFoundException.class, ErrorType.NOT_FOUND
                    //InvalidInputException.class, ErrorType.BAD_REQUEST
            );

            @Override
            protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
                ErrorType errorType = exceptionMappings.get(ex.getClass());

                if (errorType != null) {
                    return GraphqlErrorBuilder.newError()
                            .message(ex.getMessage())                          // сообщение ошибки
                            .errorType(errorType)                              // тип ошибки
                            .path(env.getExecutionStepInfo().getPath())        // путь в GraphQL-запросе
                            .extensions(Map.of("exceptionClass", ex.getClass().getSimpleName())) // доп. инфо
                            .build();
                }

                // Для всех остальных ошибок возвращаем null → Spring GraphQL отобразит INTERNAL_ERROR
                return null;
            }
        };
    }
}