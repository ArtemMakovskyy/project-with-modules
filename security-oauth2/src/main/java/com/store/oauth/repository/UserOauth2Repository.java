package com.store.oauth.repository;

import com.store.oauth.model.User;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class UserOauth2Repository {

    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        User user1 = new User();
        user1.setId("1");
        user1.setGiven_name("Artem");
        user1.setFamily_name("Makovskyi");
        user1.setName("Artem Makovskyi");
        user1.setEmail("artem@example.com");
        user1.setPicture("https://example.com/artem.png");

        User user2 = new User();
        user2.setId("2");
        user2.setGiven_name("Anna");
        user2.setFamily_name("Ivanova");
        user2.setName("Anna Ivanova");
        user2.setEmail("anna@example.com");
        user2.setPicture("https://example.com/anna.png");

        User user3 = new User();
        user3.setId("3");
        user3.setGiven_name("John");
        user3.setFamily_name("Doe");
        user3.setName("John Doe");
        user3.setEmail("john@example.com");
        user3.setPicture("https://example.com/john.png");

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
}
