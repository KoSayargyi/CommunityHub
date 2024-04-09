package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community,Long>, JpaSpecificationExecutor<Community> {

}
