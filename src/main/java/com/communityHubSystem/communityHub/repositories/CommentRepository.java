package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
