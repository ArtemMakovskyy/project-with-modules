// file: marketplaces-service/src/main/java/com/store/service/EbayService.java
package com.store.market.service;

import com.store.market.client.EbayBrowseClient;
import org.springframework.stereotype.Service;

@Service
public class EbayService {

    private final EbayBrowseClient ebayBrowseClient;

    public EbayService(EbayBrowseClient ebayBrowseClient) {
        this.ebayBrowseClient = ebayBrowseClient;
    }

    public String search(String query, String accessToken) {
        return ebayBrowseClient.searchItems(query, "Bearer " + accessToken);
    }
}
