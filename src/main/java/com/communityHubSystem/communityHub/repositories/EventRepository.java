package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
