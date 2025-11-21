package com.store.graph.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphTestController {

    @GetMapping("/api/graph")
    public String graph(){
        return "Hello World";
    }
}
