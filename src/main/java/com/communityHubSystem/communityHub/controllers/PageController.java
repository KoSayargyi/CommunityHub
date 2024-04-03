package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String homePage(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getAuthorities()
                .stream().anyMatch(a-> ((GrantedAuthority) a)
                        .getAuthority().equals(User.Role.ADMIN.name()) ||
                        ((GrantedAuthority) a)
                                .getAuthority()
                                .equals(User.Role.USER.name()))){
            return "index";
        }else{
            return "/layout/login";
        }
    }
}
