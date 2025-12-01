package com.store.ai.service;

import java.util.UUID;

public interface AiChatMessageService {
    String ask(UUID chatId, String question);
}
