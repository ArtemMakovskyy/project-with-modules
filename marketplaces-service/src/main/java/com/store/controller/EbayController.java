// file: marketplaces-service/src/main/java/com/store/controller/EbayController.java
package com.store.controller;

import com.store.service.EbayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbayController {

    private final EbayService ebayService;

    public EbayController(EbayService ebayService) {
        this.ebayService = ebayService;
    }

    @GetMapping("/ebay/search")
    public String search(@RequestParam String q) {
        String token = "YOUR_ACCESS_TOKEN"; // отримати через OAuth2
        return ebayService.search(q, token);
    }
}
