package com.communityHubSystem.communityHub.services;
import com.communityHubSystem.communityHub.DTO.PublicPostDTO;
import com.communityHubSystem.communityHub.models.Post;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface PostService {
    public Post createPublicPost(PublicPostDTO post, MultipartFile[] resource) throws IOException;

    public List<Post> findPostByUserId(Long id);

    public List<Post> getAll();
}
