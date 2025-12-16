package com.store.market.client;

import com.store.market.dto.EbayToken;
import com.store.market.dto.EbayTokenResponse;
import com.store.market.repository.EbayClientCredentialRepository;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class EbayClient {

    @Value("${ebay.token-url}")
    private String tokenUrl;

    private final RestTemplate restTemplate;
    private final EbayClientCredentialRepository ebayClientCredentialRepository;

    public EbayToken updateToken(EbayToken ebayToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String credentials = Base64.getEncoder().encodeToString(
                (ebayClientCredentialRepository.getEbayClientId() + ":" +
                        ebayClientCredentialRepository.getEbayClientSecret()).getBytes()
        );
        headers.set("Authorization", "Basic " + credentials);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", ebayToken.getRefreshToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<EbayTokenResponse> response =
                restTemplate.postForEntity(tokenUrl, request, EbayTokenResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            EbayTokenResponse resp = response.getBody();
            long expiryTime = System.currentTimeMillis() + (resp.getExpiresIn() * 1000L);

            return new EbayToken(resp.getAccessToken(), ebayToken.getRefreshToken(), expiryTime);
        } else {
            throw new RuntimeException("Failed to fetch access token: " + response);
        }
    }
}
