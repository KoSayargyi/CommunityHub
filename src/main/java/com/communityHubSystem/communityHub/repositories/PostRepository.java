package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Post,Long>, JpaSpecificationExecutor<Post> {
}
