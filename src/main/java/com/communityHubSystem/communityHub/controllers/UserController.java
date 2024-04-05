package com.communityHubSystem.communityHub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/goToCreatePost")
    public String goToCreatePost(){
        return "/pages-blank";
    }
}
