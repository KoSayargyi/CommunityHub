package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public User updateUserData(User user);
    public List<User> getAllUser();
    public List<User> searchMethod(Long id,
                                   Long doorLogNum,
                                   String staffId,
                                   String chatRoomName,
                                   String name,
                                   String gender,
                                   String email,
                                   String phone,
                                   String role,
                                   String groupName,
                                   String postDescription,
                                   String team,
                                   String division,
                                   String department,
                                   boolean isActive,
                                   List<String> hobbyNameList,
                                   List<String> policyRuleList,
                                   List<String> skillNameList);
}
