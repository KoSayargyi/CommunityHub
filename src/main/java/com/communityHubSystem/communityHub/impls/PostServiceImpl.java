package com.communityHubSystem.communityHub.impls;

import com.cloudinary.Cloudinary;
import com.communityHubSystem.communityHub.dto.PostDTO;
import com.communityHubSystem.communityHub.exception.CommunityHubException;
import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;
import com.communityHubSystem.communityHub.repositories.PollRepository;
import com.communityHubSystem.communityHub.repositories.PostRepository;
import com.communityHubSystem.communityHub.repositories.ResourceRepository;
import com.communityHubSystem.communityHub.services.PostService;
import com.communityHubSystem.communityHub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {


    private final PollRepository pollRepository;
    private final PostRepository postRepository;
    private final Cloudinary cloudinary;
    private final ResourceRepository resourceRepository;
    private final UserService userService;
    private final List<String> photoExtensions = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", "bmp","tiff","tif","psv","svg","webp","ico","heic");
    private final List<String> videoExtensions = Arrays.asList(".mp4", ".avi", ".mov", ".wmv" ,"mkv" ,"flv","mpeg","mpg","webm","3gp","ts");


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
    public Post createPost(PostDTO postDTO,MultipartFile[] files,String[] captions) throws IOException {
        var resources = files;
        var savedUrl = "";
        var staffId = SecurityContextHolder.getContext().getAuthentication().getName();
        var user =  userService.findByStaffId(staffId).orElseThrow(() -> new CommunityHubException("user not found"));
        var post = new Post();

        post.setPostType(checkPostType(postDTO));
        post.setCreatedDate(new Date());
        post.setDescription(postDTO.getContent());
        post.setUser(user);
//        post.setPostType(checkPostType(postDTO));
        post.setPostType(Post.PostType.EVENT);
        var savedPost = postRepository.save(post);


        for(int i = 0; i<resources.length; i++){
            var resource = resources[i];
            var extension =  resource.getOriginalFilename().substring(resource.getOriginalFilename().lastIndexOf('.')).toLowerCase();
            var saveResource = new Resource();
            saveResource.setPost(savedPost);
            saveResource.setDescription(captions[i]);
            if(isValidPhotoExtension(extension)){
                savedUrl = uploadPhoto(resource);
                saveResource.setPhoto(savedUrl);
            }else
            if(isValidVideoExtension(extension)){
                savedUrl = uploadVideo(resource);
                saveResource.setVideo(savedUrl);
            }else {
                savedUrl = null;
            }
            saveResource.setDate(new Date());
            resourceRepository.save(saveResource);
        }
        return savedPost;
    }



    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
//    return postRepository.findAllWithResources();
    }

//    @Override
//    public Post updatePost(PostUpdateDTO postUpdateDTO) {
//
//        return null;
//    }


//    @Override
//    public Post deletePost(Long id) {
//        return null;
//    }

    private boolean isValidPhotoExtension(String extension) {
        return photoExtensions.contains(extension);
    }

    private boolean isValidVideoExtension(String extension) {
        return videoExtensions.contains(extension);
    }

    private Post.PostType checkPostType(PostDTO postDTO){
        switch (postDTO.getPostType()){
            case "CONTENT" -> {
                return Post.PostType.CONTENT;
            }
            case "RESOURCE" -> {
                return Post.PostType.RESOURCE;
            }
            case "EVENT" -> {
                return Post.PostType.EVENT;
            }
            case "POLL" -> {
                return Post.PostType.POLL;
            }
            default -> {
                return null;
            }
        }
    }

//    private void updateContentPost(PostUpdateDTO postUpdateDTO){
//        var contentPost = postRepository.findById(postUpdateDTO.getPostId()).orElseThrow(()->new CommunityHubException("post not found"));
//        contentPost.setDescription(postUpdateDTO.getDescription());
//        postRepository.save(contentPost);
//    }
//    private void updateResourcePost(PostUpdateDTO postUpdateDTO){
//        var resource = postUpdateDTO.getResources();
//        var post = postRepository.findById(postUpdateDTO.getPostId()).orElseThrow(()->new CommunityHubException("post not found"));
//        for(var r : resource){
//            var updateResource = resourceRepository.findById(r.getId()).orElseThrow(()->new CommunityHubException("resource not found"));
//            updateResource.setDate(new Date());
//            updateResource.setPost(post);
//            updateResource.setPhoto(r.getPhoto());
//            updateResource.setVideo(r.getVideo());
//            updateResource.setReacts(r.getReacts());
//            updateResource.setDescription(r.getDescription());
//            updateResource.setShares(r.getShares());
//            updateResource.setComments(r.getComments());
//            resourceRepository.save(updateResource);
//        }
//    }
//    private void updateEventPost(PostUpdateDTO postUpdateDTO){
//        var eventPost = postRepository.findById(postUpdateDTO.getPostId()).orElseThrow(()->new CommunityHubException("post not found"));
//        eventPost.setDescription(postUpdateDTO.getDescription());
//        eventPost.setStart_date(postUpdateDTO.getStart_date());
//        eventPost.setEnd_date(postUpdateDTO.getEnd_date());
//        postRepository.save(eventPost);
//    }
//    private void updatePollPost(PostUpdateDTO postUpdateDTO){
//
//    }
}
