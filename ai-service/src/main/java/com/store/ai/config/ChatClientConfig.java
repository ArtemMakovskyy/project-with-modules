package com.store.ai.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    @Bean
    public ChatClient chatClientCustom(ChatClient.Builder builder) {
        return builder
                .defaultOptions(
                        MistralAiChatOptions.builder()
                                .temperature(0.3) //больше - гонит
                                .topP(0.7) //70% варианты совпадения достаточно для поиска
                                .maxTokens(400)
                                .build()
                )
                .build();
    }

}
