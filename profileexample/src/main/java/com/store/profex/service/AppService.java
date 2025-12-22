package com.store.profex.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

    private final MessageService messageService;

    public void run() {
        System.out.println(messageService.getMessage());
    }
}