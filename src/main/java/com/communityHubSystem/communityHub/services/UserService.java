package com.communityHubSystem.communityHub.services;

import com.communityHubSystem.communityHub.dto.UserDTO;
import com.communityHubSystem.communityHub.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void updateUserData(User user);
    public List<User> getAllUser();
    public List<User> searchMethod(UserDTO userDTO);

    Optional<User> findByStaffId(String staffId);

    User findById(Long id);

    void saveUserToDatabase(MultipartFile file);
}
