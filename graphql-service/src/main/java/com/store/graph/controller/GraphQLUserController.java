package com.store.graph.controller;

import com.store.graph.dto.CreateUserInput;
import com.store.graph.dto.FilterInput;
import com.store.graph.model.UserEntity;
import com.store.graph.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequiredArgsConstructor
public class GraphQLUserController {

    private final UserService userService;

    /*
    Используется для методов, которые соответствуют полям в type Query вашей GraphQL-схемы.
    То есть GraphQL-запросы (чтение данных) маппятся сюда.
     */
    @QueryMapping
    public List<UserEntity> users(
            /*
            Помечает параметр метода как аргумент GraphQL-запроса.
            "filter" — это имя аргумента в схеме (filter: FilterInput).
            Spring GraphQL сам преобразует JSON-объект из запроса в Java-класс FilterInput.
             */
            @Argument("filter") FilterInput filter
    ) {

        System.out.println(filter);

        return userService.getAllUsers(filter);
    }

    @QueryMapping
    public UserEntity user(
            @Argument UUID id
    ) {
        return userService.getUser(id);
    }

    //Используется для методов, которые реализуют GraphQL-мутации (изменение данных).
    @MutationMapping
    @Transactional
    public UserEntity createUser(
            @Argument CreateUserInput input
    ) {
        return userService.createUser(input);
    }
}