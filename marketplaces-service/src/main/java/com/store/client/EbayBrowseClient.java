// file: marketplaces-service/src/main/java/com/store/client/EbayBrowseClient.java
package com.store.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ebayBrowseClient", url = "https://api.ebay.com")
public interface EbayBrowseClient {

    @GetMapping("/buy/browse/v1/item_summary/search")
    String searchItems(
            @RequestParam("q") String query,
            @RequestHeader("Authorization") String authHeader
    );
}
