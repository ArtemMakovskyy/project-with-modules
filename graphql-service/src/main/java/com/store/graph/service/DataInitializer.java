package com.store.graph.service;

import com.store.graph.model.Role;
import com.store.graph.model.UserEntity;
import com.store.graph.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitializer {


    private final UserRepository userRepository;


    @PostConstruct
    public void init() {
        if (userRepository.count() > 0) return;


        UserEntity alice = UserEntity.builder()
                .name("Alice")
                .email("alice@example.com")
                .roles(List.of(Role.ADMIN))
                .build();


        UserEntity bob = UserEntity.builder()
                .name("Bob")
                .email("bob@example.com")
                .roles(List.of(Role.USER))
                .build();


        userRepository.saveAll(List.of(alice, bob));

    }
}