package com.store.ai.config;

import com.store.ai.model.AIRole;
import com.store.ai.model.AiChat;
import com.store.ai.model.AiChatMessage;
import com.store.ai.service.AiChatService;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;

@RequiredArgsConstructor
@Builder
public class CustomPostgresChatMemory implements ChatMemory {

    private final AiChatService aiChatService;
    private final int maxMessages;

    /**
     * Додає список повідомлень до чату.
     * - Отримує чат за conversationId
     * - Перевіряє кожне повідомлення на дублікати
     * - Якщо повідомлення нове — додає його
     * - Зберігає оновлений чат
     */
    @Override
    public void add(String conversationId, List<Message> messages) {
        AiChat chat = aiChatService.getChat(UUID.fromString(conversationId));

        for (Message message : messages) {
            if (!messageExists(chat, message)) {
                chat.getAiChatMessages().add(toAiChatMessage(chat, message));
            }
        }

        aiChatService.saveChat(chat);
    }

    /**
     * Перевіряє, чи повідомлення вже існує в чаті.
     * Критерій: однаковий текст і роль.
     */
    private boolean messageExists(AiChat chat, Message message) {
        return chat.getAiChatMessages().stream()
                .anyMatch(existing ->
                        existing.getText().equals(message.getText()) &&
                                existing.getAiRole() == getAirole(message));
    }

    /**
     * Створює новий об’єкт AiChatMessage на основі Message.
     */
    private AiChatMessage toAiChatMessage(AiChat chat, Message message) {
        return AiChatMessage.builder()
                .aiChat(chat)
                .text(message.getText())
                .aiRole(getAirole(message))
                .build();
    }



    @Override
    public List<Message> get(String conversationId) {

        AiChat chat = aiChatService.getChat(UUID.fromString(conversationId));

        return chat.getAiChatMessages().stream()
                .skip(Math.max(0, chat.getAiChatMessages().size() - maxMessages))
                .map(this::getMessage)
                .limit(maxMessages)
                .toList();
    }

    @Override
    public void clear(String conversationId) {

    }

    private Message getMessage(AiChatMessage aiChatMessage) {
        switch (aiChatMessage.getAiRole()) {
            case USER -> {
                return new UserMessage(aiChatMessage.getText());
            }
            case ASSISTANT -> {
                return new AssistantMessage(aiChatMessage.getText());
            }
            case SYSTEM -> {
                return new SystemMessage(aiChatMessage.getText());
            }
            default -> {
                return null;
            }
        }
    }

    private AIRole getAirole(Message message){
        switch (message.getMessageType()){
            case USER -> {
                return AIRole.USER;
            }
            case ASSISTANT -> {
                return AIRole.ASSISTANT;
            }
            case SYSTEM -> {
                return AIRole.SYSTEM;
            }
            default -> {
                return null;
            }
        }
    }
}
