package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {
}
