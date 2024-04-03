package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
