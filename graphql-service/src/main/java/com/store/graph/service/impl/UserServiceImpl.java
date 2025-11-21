package com.store.graph.service.impl;

import com.store.graph.dto.CreateUserInput;
import com.store.graph.dto.FilterInput;
import com.store.graph.exception.UserNotFoundException;
import com.store.graph.model.UserEntity;
import com.store.graph.repository.UserRepository;
import com.store.graph.service.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers(FilterInput filter) {

        System.out.println(filter);

        Pageable pageable = PageRequest.of(
                filter.getOffsetOrDefault(),
                filter.getLimitOrDefault(),
                Sort.by(Sort.Direction.ASC, filter.getSortFieldOrDefault())
        );

        return userRepository.findAll(pageable).stream().toList();
    }

    @Override
    public UserEntity getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Сущность не найдена")
        );
    }

    @Override
    @Transactional
    public UserEntity createUser(CreateUserInput input) {

        UserEntity userEntity = UserEntity.builder()
                .name(input.name())
                .email(input.email())
                .roles(List.of(input.role()))
                .build();

        return userRepository.save(userEntity);
    }
}
