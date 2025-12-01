package com.store.ai.controller;

import com.store.ai.dto.RequestDto;
import com.store.ai.service.impl.AiChatMessageServiceImpl;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiChatMessageServiceImpl aiChatMessageService;

    @PostMapping("/api/chat/new")
    public UUID newChat() {
        return UUID.randomUUID();
    }

    @PostMapping("/api/ask")
    public String ask(@RequestBody RequestDto requestDto) {
        return aiChatMessageService.ask(
                requestDto.chatId(),
                requestDto.question()
        );
    }

}
