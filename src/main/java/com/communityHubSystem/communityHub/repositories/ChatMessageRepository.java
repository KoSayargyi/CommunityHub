package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
}
