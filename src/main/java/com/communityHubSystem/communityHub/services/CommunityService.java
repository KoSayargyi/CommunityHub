package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.models.Community;
import org.springframework.stereotype.Service;

@Service
public interface CommunityService {
    public void createCommunity(Community community, Long id);
}
