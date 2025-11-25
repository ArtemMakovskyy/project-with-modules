package com.store.ai.controller;

import com.store.ai.dto.RequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final ChatClient chatClient;

    @PostMapping("/api/chat/new")
    public UUID newChat() {
        return UUID.randomUUID();
    }

    @PostMapping("/api/ask")
    public String ask(@RequestBody RequestDto requestDto) {
        return chatClient.prompt()
                .advisors(
                        advisorSpec -> advisorSpec.param(
                                ChatMemory.CONVERSATION_ID,
                                requestDto.chatId()
                        )
                )
                .user(
                        requestDto.question()
                )
                .call()
                .content();
    }
}