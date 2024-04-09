package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Community;
import com.communityHubSystem.communityHub.models.User_Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community,Long> {
}
