package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;
import com.communityHubSystem.communityHub.repositories.PostRepository;
import com.communityHubSystem.communityHub.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Override
    public void createPublicPost(Post post, Resource resource) {

    }

    @Override
    public List<Post> findPostByUserId(Long id) {
         return postRepository.findPostByUserId(id);
    }
}
