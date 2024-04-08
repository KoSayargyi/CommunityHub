package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.dto.EventDTO;
import com.communityHubSystem.communityHub.exception.CommunityHubException;
import com.communityHubSystem.communityHub.models.Access;
import com.communityHubSystem.communityHub.models.Event;
import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.repositories.EventRepository;
import com.communityHubSystem.communityHub.repositories.PollRepository;
import com.communityHubSystem.communityHub.repositories.UserRepository;
import com.communityHubSystem.communityHub.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final PollRepository pollRepository;
    private final UserRepository userRepository;

    @Override
    public Event createEvent(EventDTO eventDTO) {
        if(eventDTO.getEventType().equals("EVENT")){
            return createEventPost(eventDTO);
        }else{
            return createPollPost(eventDTO);
        }
    }

    private Event createEventPost(EventDTO eventDTO){
        var event = new Event();
        event.setEventType(Event.EventType.EVENT);
        event.setAccess(checkAccess(eventDTO));
        event.setCreated_date(new Date());
        event.setDescription(eventDTO.getDescription());
        event.setStart_date(eventDTO.getStart_date());
        event.setEnd_date(eventDTO.getEnd_date());
        event.setUser(getLoginUser());
        return eventRepository.save(event);
    }

    private Event createPollPost(EventDTO eventDTO){
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
