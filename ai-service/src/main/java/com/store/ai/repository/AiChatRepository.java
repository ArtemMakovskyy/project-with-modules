package com.store.ai.repository;

import com.store.ai.model.AiChat;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiChatRepository extends JpaRepository<AiChat, UUID> {
}
