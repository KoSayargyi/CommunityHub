package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.dto.PostDTO;
import com.communityHubSystem.communityHub.repositories.PostRepository;
import com.communityHubSystem.communityHub.repositories.ResourceRepository;
import com.communityHubSystem.communityHub.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException; 
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
    public ResponseEntity<?> createPublicPost(@ModelAttribute PostDTO publicPostDTO) throws IOException {
        var  post =  postService.createPost(publicPostDTO);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }
}
