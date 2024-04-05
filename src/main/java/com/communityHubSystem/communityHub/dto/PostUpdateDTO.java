package com.communityHubSystem.communityHub.dto;

import com.communityHubSystem.communityHub.models.Poll;
import com.communityHubSystem.communityHub.models.Post;
import com.communityHubSystem.communityHub.models.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDTO {
    private Long postId;
    private Poll poll;
    private Resource[] resources;
    private String description;
    private String postType;
    private Date start_date;
    private Date end_date;
}
