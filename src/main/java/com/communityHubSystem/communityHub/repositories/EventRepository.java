package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByEventType(Event.EventType eventType);
}
