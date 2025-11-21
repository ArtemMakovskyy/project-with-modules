package com.store.graph.dto;

import com.store.graph.model.Role;

public record   CreateUserInput(
        String name,
        String email,
        Role role
) {
}