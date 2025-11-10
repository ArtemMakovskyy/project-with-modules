package com.store;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @PostConstruct
    public void init(){
        System.out.println("PostConstruct");
    }
}
