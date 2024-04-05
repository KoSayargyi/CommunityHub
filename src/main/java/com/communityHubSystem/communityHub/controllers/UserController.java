package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.dto.UserDTO;
import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> giveAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user){
        userService.updateUserData(user);
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }

    @PostMapping("/search")
    public ResponseEntity<List<User>> searchMethod(@RequestBody UserDTO userDTO){
        System.err.println(userDTO);
        userDTO.isActive();
        return ResponseEntity.ok(userService.searchMethod(userDTO));
    }

    @PostMapping("/upload-data")
    public ResponseEntity<?> uploadUsersData(@RequestParam("file") MultipartFile file) throws IOException {
        this.userService.saveUserToDatabase(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Employee data uploaded and saved to database successfully"));
    }


}
