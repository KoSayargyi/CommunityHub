package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.models.Community;
import com.communityHubSystem.communityHub.models.User_Group;
import com.communityHubSystem.communityHub.repositories.CommunityRepository;
import com.communityHubSystem.communityHub.repositories.UserRepository;
import com.communityHubSystem.communityHub.repositories.User_GroupRepository;
import com.communityHubSystem.communityHub.services.CommunityService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final User_GroupRepository user_groupRepository;


    @Override
    public void createCommunity(Community community,Long id) {
        MultipartFile file = community.getFile();
        var user = userRepository.findById(id).orElseThrow();
        community.setOwnerName(user.getName());
        if(file != null){
            try {
                byte[] photoByte = file.getBytes();
                community.setImage(photoByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       var com = communityRepository.save(community);
        User_Group user_group = new User_Group();
         user_group.setUser(user);
         user_group.setCommunity(com);
         user_groupRepository.save(user_group);
    }

}
