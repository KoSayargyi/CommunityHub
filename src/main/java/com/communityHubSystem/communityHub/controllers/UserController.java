package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> giveAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }


}
