package com.store.market.dto;

import lombok.Value;

@Value
public class EbayToken {
    String accessToken;
    String refreshToken;
    long expiryTime;
}
