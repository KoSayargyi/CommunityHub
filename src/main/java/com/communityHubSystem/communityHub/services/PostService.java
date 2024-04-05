package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;

import java.util.List;

public interface PostService {
    public void createPublicPost(Post post, Resource resource);

    public List<Post> findPostByUserId(Long id);
}
