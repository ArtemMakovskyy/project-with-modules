package com.store.profex.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"test", "local"})
public class TestMessageService implements MessageService {

    @Override
    public String getMessage() {
        return "Hello from TEST/LOCAL profile";
    }
}
