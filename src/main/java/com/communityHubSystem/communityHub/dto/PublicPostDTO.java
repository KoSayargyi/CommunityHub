package com.communityHubSystem.communityHub.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicPostDTO {
    private String content;
    private MultipartFile file;
//    private MultipartFile[] files;

}
