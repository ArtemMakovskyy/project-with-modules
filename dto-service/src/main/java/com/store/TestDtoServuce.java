package com.store;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TestDtoServuce {
    @PostConstruct
    public void init(){
        testDtoServuce();
    }

    public void testDtoServuce() {
        System.out.println("TestDtoServuce constructor");
    }
}
