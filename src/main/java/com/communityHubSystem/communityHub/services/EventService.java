package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.dto.EventDTO;
import com.communityHubSystem.communityHub.models.Event;

public interface EventService {
    public Event createEvent(EventDTO eventDTO);
}
