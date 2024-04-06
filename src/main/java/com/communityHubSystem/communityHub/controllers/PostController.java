package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.DTO.PublicPostDTO;
import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping("/createdPost")
    public ResponseEntity createPublicPost(@ModelAttribute PublicPostDTO publicPostDTO, @RequestParam("files") MultipartFile[] files) throws IOException {
        var  post =  postService.createPublicPost(publicPostDTO, files);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @GetMapping("/postview")
    @ResponseBody
    public List<Post> posts(){
       List<Post> posts=postService.getAll();
       return posts;
    }
}
