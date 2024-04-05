package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.DTO.PublicPostDTO;
import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;
import com.communityHubSystem.communityHub.repositories.PostRepository;
import com.communityHubSystem.communityHub.repositories.ResourceRepository;
import com.communityHubSystem.communityHub.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @PostMapping("/createPublicPost")
    public ResponseEntity<?> createPublicPost(@ModelAttribute PublicPostDTO publicPostDTO) throws IOException {
        System.err.println(publicPostDTO);
//        var p = new Post();
//        var r = new Resource();
//        r.setId(1L);
//        r.setPhoto("url");
//        r.setDescription("photo");
//        p.setCreated_date(new Date());
//        p.setId(1L);
//        p.setDescription("post");
//        p.setPostType(Post.PostType.CONTENT);
//       var post =  postRepository.save(p);
//        r.setPost(post);
//        resourceRepository.save(r);
//        postService.createPublicPost(publicPostDTO);
        var  post =  postService.createPublicPost(publicPostDTO);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }
}
