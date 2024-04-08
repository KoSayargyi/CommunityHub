package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community,Long> {

    @Query("SELECT c.image FROM Community c WHERE c.id = :id")
    byte[] originalPhoto(Long id);
}
