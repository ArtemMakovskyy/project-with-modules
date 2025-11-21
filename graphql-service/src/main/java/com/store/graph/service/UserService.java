package com.store.graph.service;

import com.store.graph.dto.CreateUserInput;
import com.store.graph.dto.FilterInput;
import com.store.graph.model.UserEntity;
import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserEntity> getAllUsers(FilterInput filter);

    UserEntity getUser(UUID id);

    UserEntity createUser(CreateUserInput input);
}
