package com.store.ai.repository;

import com.store.ai.model.AiChatMessage;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiChatMessageRepository extends JpaRepository<AiChatMessage, UUID> {
}
