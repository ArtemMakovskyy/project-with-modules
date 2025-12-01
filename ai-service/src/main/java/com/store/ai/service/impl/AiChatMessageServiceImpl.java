package com.store.ai.service.impl;

import com.store.ai.service.AiChatMessageService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiChatMessageServiceImpl implements AiChatMessageService {

    private final ChatClient chatClient;

    @Override
    public String ask(UUID chatId, String question) {
        return chatClient.prompt()
                .advisors(
                        advisorSpec -> advisorSpec.param(
                                ChatMemory.CONVERSATION_ID,
                                chatId))
                .user(question)
                .call()
                .content();
    }
}

