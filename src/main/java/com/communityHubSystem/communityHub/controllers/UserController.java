package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/upload-data")
    public ResponseEntity<?> uploadUserData(@RequestParam("file") MultipartFile file) throws IOException {
        userService.saveUserToDatabase(file);
        return ResponseEntity
                .ok(Map.of("Message" , " User data uploaded and saved to database successfully"));
    }


}
