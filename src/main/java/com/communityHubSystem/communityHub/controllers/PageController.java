package com.communityHubSystem.communityHub.controllers;

import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private UserService userService;




    @GetMapping("/")
    public String homePage(HttpSession session){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String staffId = auth.getName();
        Optional<User> user = userService.findByStaffId(staffId);
        if(user.isPresent() && auth.getAuthorities()
                .stream().anyMatch(a-> ((GrantedAuthority) a)
                        .getAuthority().equals(User.Role.ADMIN.name()) ||
                        ((GrantedAuthority) a)
                                .getAuthority()
                                .equals(User.Role.USER.name()))){
            session.setAttribute("user", user.get()); // Store user object in session
            return "redirect:/index";
        }else{
            return "/layout/login";
        }
    }

    @GetMapping("/index")
    public String indexPage(HttpSession session) {
        // Retrieve user object from session
        User user = (User) session.getAttribute("user");
        if(user == null) {
            // Handle case where user object is not found in session
            return "redirect:/login"; // Redirect to login page or handle as appropriate
        }
        return "/layout/test";
    }
    @GetMapping("/video")
    public String videoPage() {
        return "/layout/video";
    }
}
