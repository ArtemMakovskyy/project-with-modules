package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Исправляем TimeZone до старта Spring Boot
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
        // или: java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("Europe/Kyiv"));

        SpringApplication.run(Application.class, args);
    }
}
