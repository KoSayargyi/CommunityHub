package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;

public interface PostService {
    public void createPublicPost(Post post, Resource resource);
}
