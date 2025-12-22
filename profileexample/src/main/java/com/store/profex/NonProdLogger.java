package com.store.profex;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!prod")
public class NonProdLogger {

    public void log(String text) {
        System.out.println("Non-prod log: " + text);
    }
}
