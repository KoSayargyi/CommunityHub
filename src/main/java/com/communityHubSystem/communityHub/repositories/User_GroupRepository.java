package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.User_Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_GroupRepository extends JpaRepository<User_Group,Long> {

    List<User_Group> findByCommunityId(Long communityId);
}
