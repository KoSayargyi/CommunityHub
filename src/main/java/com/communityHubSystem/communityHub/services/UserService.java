package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.dto.UserDTO;
import com.communityHubSystem.communityHub.models.User;

import java.util.List;

public interface UserService {

    public void updateUserData(User user);
    public List<User> getAllUser();
    public List<User> searchMethod(UserDTO userDTO);
    public User findByStaffId(String staffId);
    public User findById(Long id);
}
