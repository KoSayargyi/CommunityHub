package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.User_Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface User_GroupRepository extends JpaRepository<User_Group,Long> {

    List<User_Group> findByCommunityId(Long communityId);

    @Query (value = "select distinct community_id from user_group where user_id = :userId",nativeQuery = true)
    List<Long> findDistinctCommunityIdByUserId(Long userId);
}
