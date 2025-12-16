package com.store.market.repository;

import com.store.market.secret.SecretData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EbayClientCredentialRepository {
    private final SecretData secretData;

    public String getEbayClientId() {
        return secretData.getEbayClientId();
    }

    public String getEbayClientSecret() {
        return secretData.getEbayClientSecret();
    }

    public String getEbayRefreshToken() {
        return secretData.getEbayRefreshToken();
    }
}
