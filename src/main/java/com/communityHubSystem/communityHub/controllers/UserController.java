package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.dto.UserDTO;
import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.repositories.UserRepository;
import com.communityHubSystem.communityHub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/allUser")
    @ResponseBody
    public ResponseEntity<List<User>> giveAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody User user){
        userService.updateUserData(user);
        return ResponseEntity.status(HttpStatus.OK).body("Ok");
    }

    @PostMapping("/search")
    @ResponseBody
    public ResponseEntity<List<User>> searchMethod(@RequestBody UserDTO userDTO){
        System.err.println(userDTO);
        userDTO.isActive();
        return ResponseEntity.ok(userService.searchMethod(userDTO));
    }

    @GetMapping("/profile")
    public String profilePage(Model model){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String staffId = auth.getName();
        var user = userService.findByStaffId(staffId).orElseThrow();
        System.out.println(user);
        System.out.println(user.getPosts().size());
     model.addAttribute("user", user);
        return "/user/user-profile";
    }
    @PostMapping("/upload-data")
    @ResponseBody
    public ResponseEntity<?> uploadUsersData(@RequestParam("file") MultipartFile file) throws IOException {
        this.userService.saveUserToDatabase(file);
        return ResponseEntity
                .ok(Map.of("Message" , " Employee data uploaded and saved to database successfully"));
    }

    @GetMapping("/View-all-users")
    public String viewUser(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/user/view-all-user";
    }

    @PostMapping("/updateUserStatus")
    @ResponseBody
    public String updateUserStatus(@RequestParam Long userId, @RequestParam boolean isActive) {
        userService.updateUserStatus(userId, isActive);
        return "User status updated successfully";
    }

}
