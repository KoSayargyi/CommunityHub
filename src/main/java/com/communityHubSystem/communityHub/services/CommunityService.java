package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.models.Community;
import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.models.User_Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface CommunityService {
    public void createCommunity(Community community, Long id);

    public List<User> getAll();

    public List<Community> getAllCommunity(Model model);

    public Community getCommunityBy(Long id);

   public void createGroup(Community community,List<Long> id);

    public List<String> getOwnerNamesByCommunityId(Long communityId);

    public List<Community> getAllCommunityWithUserId();
}
