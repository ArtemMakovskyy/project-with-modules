package com.store.market.service;

import com.store.market.client.EbayClient;
import com.store.market.dto.EbayToken;
import com.store.market.repository.EbayTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EbayTokenService {

    private final EbayTokenRepository ebayTokenRepository;
    private final EbayClient ebayClient;

    public String getAccessToken() {
        EbayToken ebayToken = ebayTokenRepository.getEbayToken();

        if (isTokenExpiredOrMissing(ebayToken)) {
            ebayToken = ebayClient.updateToken(ebayToken);
            ebayTokenRepository.save(ebayToken);
        }

        return ebayToken.getAccessToken();
    }

    private boolean isTokenExpiredOrMissing(EbayToken ebayToken) {
        return ebayToken.getAccessToken() == null ||
                System.currentTimeMillis() >= ebayToken.getExpiryTime();
    }
}
