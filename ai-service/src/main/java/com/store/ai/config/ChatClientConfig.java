package com.store.ai.config;

import com.store.ai.config.advisor.ExpansionQueryAdvisor;
import com.store.ai.service.AiChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    private final AiChatService chatService;
    private final ExpansionQueryAdvisor  expansionQueryAdvisor;

    @Value("${app.maxMessages}")
    private int maxMessage;

    @Bean
    public ChatClient chatClientCustom(ChatClient.Builder builder) {
        return builder
                .defaultAdvisors(
                        expansionQueryAdvisor,
                        addChatMemory(1)
                )
                .defaultOptions(
                        MistralAiChatOptions.builder()
                                .temperature(0.2) //больше - гонит
                                .topP(0.7) //70% варианты совпадения достаточно для поиска
                                .maxTokens(400)
                                .build()
                )
                .build();
    }

    private Advisor addChatMemory(int order) {
        return MessageChatMemoryAdvisor.builder(getPostgresChatMemory())
                .order(order)
                .build();
    }

    private Advisor addPostgresAdvisor(int order) {
        return MessageChatMemoryAdvisor.builder(getPostgresChatMemory())
                .order(order)
                .build();
    }

    private ChatMemory getPostgresChatMemory() {
        return CustomPostgresChatMemory.builder()
                .maxMessages(maxMessage)
                .chatService(chatService)
                .build();
    }

}
