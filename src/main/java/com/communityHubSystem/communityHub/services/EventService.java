package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.dto.EventDTO;
import com.communityHubSystem.communityHub.dto.PollDto;
import com.communityHubSystem.communityHub.models.Event;

import java.text.ParseException;
import java.util.List;

public interface EventService {
    public Event createEvent(EventDTO eventDTO) throws ParseException;
    public List<Event> getAllEvents();
    public void giveYES(PollDto pollDto);
    public void giveNO(PollDto pollDto);
}
