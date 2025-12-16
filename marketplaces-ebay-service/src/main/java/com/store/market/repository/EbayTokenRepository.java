package com.store.market.repository;

import com.store.market.dto.EbayToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EbayTokenRepository {

    private EbayToken ebayToken;
    private final EbayClientCredentialRepository ebayClientCredentialRepository;

    public EbayToken getEbayToken() {
        if (ebayToken == null) {
            // создаём токен только с refreshToken, accessToken пустой
            ebayToken = new EbayToken(
                    null,
                    ebayClientCredentialRepository.getEbayRefreshToken(),
                    0L
            );
        }
        return ebayToken;
    }

    public EbayToken save(EbayToken ebayTokenToSave) {
        this.ebayToken = ebayTokenToSave;
        return ebayToken;
    }
}
