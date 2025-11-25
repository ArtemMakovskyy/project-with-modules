package com.store.ai.dto;

import java.util.UUID;

public record RequestDto(
        UUID chatId,
        String question
) {
}
