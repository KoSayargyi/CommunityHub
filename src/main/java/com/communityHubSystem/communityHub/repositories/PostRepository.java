package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findPostByUserId(Long id);

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.resources")
    List<Post> findAllWithResources();
}
