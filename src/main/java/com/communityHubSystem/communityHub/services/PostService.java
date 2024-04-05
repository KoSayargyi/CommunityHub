package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.DTO.PublicPostDTO;
import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;

import java.io.IOException;

public interface PostService {
    public Post createPublicPost(PublicPostDTO publicPostDTO) throws IOException;
}
