package com.store.oauth.controller;

import com.store.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class StartOauth2Controller {
    private final UserService userService;

    @GetMapping
    public String home(){
        return "Hello!";
    }

    @GetMapping("/secured")
    public String secured(){
        return userService.save();
    }
}