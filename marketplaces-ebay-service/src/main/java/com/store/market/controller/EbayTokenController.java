package com.store.market.controller;

import com.store.market.service.EbayTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ebay/token")
public class EbayTokenController {
    private final EbayTokenService ebayTokenService;

    @GetMapping
    public String getEbayToken() {
        return ebayTokenService.getAccessToken();
    }
}
