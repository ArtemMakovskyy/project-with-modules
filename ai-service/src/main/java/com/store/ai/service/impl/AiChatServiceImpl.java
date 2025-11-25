package com.store.ai.service.impl;

import com.store.ai.model.AiChat;
import com.store.ai.repository.AiChatRepository;
import com.store.ai.service.AiChatService;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiChatServiceImpl implements AiChatService {

    private final AiChatRepository chatRepository;

    @Override
    public AiChat createChat(UUID id) {
        return chatRepository.save(AiChat.builder()
                .id(id == null ? UUID.randomUUID() : id)
                .build());
    }

    @Override
    public AiChat getChat(UUID id) {

        Optional<AiChat> byId = chatRepository.findById(id);

        return byId.orElseGet(() -> createChat(id));
    }

    @Override
    public void saveChat(AiChat chat) {
        chatRepository.save(chat);
    }
}
