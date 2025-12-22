package com.store.profex.config;

import com.store.profex.service.AppService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {

    @Bean
    @Profile({"dev", "prod", "test", "local"})
    CommandLineRunner run(AppService appService) {
        return args -> appService.run();
    }
}