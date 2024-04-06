package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.dto.PostDTO;
import com.communityHubSystem.communityHub.dto.PostUpdateDTO;
import com.communityHubSystem.communityHub.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    public Post createPost(PostDTO postDTO, MultipartFile[] files,String[] captions) throws IOException;
    public List<Post> findAllPost();


    public Post updatePost(PostUpdateDTO postUpdateDTO);
    public Post deletePost(Long id);
}
