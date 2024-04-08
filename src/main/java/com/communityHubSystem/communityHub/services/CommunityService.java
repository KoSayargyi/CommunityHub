package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.models.Community;
import com.communityHubSystem.communityHub.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public interface CommunityService {
    public void createCommunity(Community community, Long id);

    public List<User> getAll();

    public List<Community> getAllCommunity(Model model);

   public void createGroup(Community community,List<Long> id);

    @Query("SELECT c.image FROM Community c WHERE c.id = :id")
    byte[] originalPhoto(Long id);
}
