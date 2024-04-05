package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.DTO.UserDTO;
import com.communityHubSystem.communityHub.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    public void updateUserData(User user);
    public List<User> getAllUser();
    public List<User> searchMethod(UserDTO userDTO);
}
