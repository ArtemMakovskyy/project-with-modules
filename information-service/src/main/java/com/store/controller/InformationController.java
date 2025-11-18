package com.store.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/info")
public class InformationController {

    @PostConstruct
    public void init(){
        System.out.println("Information service");
    }

    @GetMapping
    public String infoTest(){
        return "Hello World";
    }

}
