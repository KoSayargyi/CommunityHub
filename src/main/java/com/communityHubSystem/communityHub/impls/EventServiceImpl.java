package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.dto.EventDTO;
import com.communityHubSystem.communityHub.exception.CommunityHubException;
import com.communityHubSystem.communityHub.models.*;
import com.communityHubSystem.communityHub.repositories.EventRepository;
import com.communityHubSystem.communityHub.repositories.PollRepository;
import com.communityHubSystem.communityHub.repositories.UserRepository;
import com.communityHubSystem.communityHub.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final PollRepository pollRepository;
    private final UserRepository userRepository;
    SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Event createEvent(EventDTO eventDTO) throws ParseException {
        if(eventDTO.getEventType().equals("EVENT")){
            return createEventPost(eventDTO);
        }else{
            return createPollPost(eventDTO);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findByEventType(Event.EventType.EVENT);
    }


    private Event createEventPost(EventDTO eventDTO) throws ParseException {
        var event = new Event();
        event.setEventType(Event.EventType.EVENT);
        event.setAccess(checkAccess(eventDTO));
        event.setCreated_date(new Date());
        event.setDescription(eventDTO.getDescription());
        event.setStart_date(formatter.parse(eventDTO.getStart_date()));
        event.setEnd_date(formatter.parse(eventDTO.getEnd_date()));
        event.setUser(getLoginUser());
        return eventRepository.save(event);
    }

    private Event createPollPost(EventDTO eventDTO) throws ParseException {

        return null;
    }

    private Access checkAccess(EventDTO eventDTO){
        if(eventDTO.getGroupId()!=null){
            return  Access.PRIVATE;
        }else {
            return Access.PUBLIC;
        }
    }

    private User getLoginUser(){
        return userRepository.findByStaffId(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()->new CommunityHubException("user not found"));
    }
}
