package com.store.controller;

import com.store.service.TestInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestInformationService testInformationService;

    @GetMapping
    public String test() {
        return testInformationService.getTestInformation();
    }
}
