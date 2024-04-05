package com.communityHubSystem.communityHub.impls;

import com.cloudinary.Cloudinary;
import com.communityHubSystem.communityHub.DTO.PublicPostDTO;
import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;
import com.communityHubSystem.communityHub.repositories.PostRepository;
import com.communityHubSystem.communityHub.repositories.ResourceRepository;
import com.communityHubSystem.communityHub.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    ResourceRepository resourceRepository;
    private final List<String> photoExtensions = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", "bmp","tiff","tif","psv","svg","webp","ico","heic");
    private final List<String> videoExtensions = Arrays.asList(".mp4", ".avi", ".mov", ".wmv" ,"mkv" ,"flv","mpeg","mpg","webm","3gp","ts");

    public void createPublicPost(Post post) {
        var resources = post.getResources();
        for(var s : resources){
            resourceRepository.save(s);
        }
        postRepository.save(post);
    }
    public String uploadVideo(MultipartFile file) throws IOException {
        return cloudinary.uploader()
                .upload(file.getBytes(), Map.of("resource_type", "video","public_id", UUID.randomUUID().toString()))
                .get("url").toString();
    }

    public String uploadPhoto(MultipartFile file) throws IOException {
        return cloudinary.uploader()
                .upload(file.getBytes(), Map.of( "public_id", UUID.randomUUID().toString()))
                .get("url").toString();
    }

    @Override
    public Post  createPublicPost(PublicPostDTO publicPostDTO) throws IOException {
        var resource = publicPostDTO.getFile();
        var savedUrl = "";
        var post = new Post();
        post.setCreated_date(new Date());
        post.setDescription(publicPostDTO.getContent());
        var savedPost = postRepository.save(post);
        var saveResource = new Resource();
        saveResource.setPost(savedPost);
        var extension =  resource.getOriginalFilename().substring(resource.getOriginalFilename().lastIndexOf('.')).toLowerCase();
        System.err.println(extension);
        if(isValidPhotoExtension(extension)){
            savedUrl = uploadPhoto(resource);
            saveResource.setPhoto(savedUrl);
            saveResource.setDescription("PHOTO");
        }else
        if(isValidVideoExtension(extension)){
            savedUrl = uploadVideo(resource);
            saveResource.setVideo(savedUrl);
            saveResource.setDescription("VIDEO");
        }else {
            savedUrl = null;
        }
        saveResource.setDate(new Date());
        resourceRepository.save(saveResource);
        return savedPost;
//        return null;
    }

    private boolean isValidPhotoExtension(String extension) {
        return photoExtensions.contains(extension);
    }

    private boolean isValidVideoExtension(String extension) {
        return videoExtensions.contains(extension);
    }
}
