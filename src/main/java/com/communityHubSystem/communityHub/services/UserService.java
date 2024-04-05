package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

 public interface UserService {
    Optional<User> findByStaffId(String username);

     void saveUserToDatabase(MultipartFile file);
     void saveUser(User user);
     List<User> getUsers();

}
