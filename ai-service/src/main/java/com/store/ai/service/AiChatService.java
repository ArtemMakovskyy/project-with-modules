package com.store.ai.service;

import com.store.ai.model.AiChat;
import java.util.UUID;

public interface AiChatService {

    AiChat createChat(UUID id);

    AiChat getChat(UUID id);

    void saveChat(AiChat chat);
}
