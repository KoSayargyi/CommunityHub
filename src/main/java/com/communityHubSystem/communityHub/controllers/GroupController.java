package com.communityHubSystem.communityHub.controllers;
import com.communityHubSystem.communityHub.models.Community;
import com.communityHubSystem.communityHub.models.User;
import com.communityHubSystem.communityHub.models.User_Group;
import com.communityHubSystem.communityHub.repositories.CommunityRepository;
import com.communityHubSystem.communityHub.repositories.UserRepository;
import com.communityHubSystem.communityHub.repositories.User_GroupRepository;
import com.communityHubSystem.communityHub.services.CommunityService;
import com.communityHubSystem.communityHub.services.UserService;
import com.communityHubSystem.communityHub.services.User_GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class GroupController {

    private final CommunityRepository communityRepository;

    private final UserRepository userRepository;

    private final User_GroupRepository user_groupRepository;

    private final CommunityService communityService;

    private final User_GroupService user_groupService;

    private final UserService userService;

    @GetMapping("/group")
    public String group(Model model){
        List<User> users = communityService.getAll();
        model.addAttribute("users", users);
        return "user/user-group";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }

    @PostMapping("/createCommunity")
    public ResponseEntity<Map<String, String>> createGroup(@ModelAttribute Community community,   @RequestParam("ownerId") Long ownerID) {
      communityService.createCommunity(community,ownerID);
      Map<String,String> response = new HashMap<>();
      response.put("message","Created successfully");
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }
     @GetMapping("viewcommunity")
     public String views(){
        return "user/community-view";
     }


    @GetMapping("/communityview")
    @ResponseBody
    public List<Community> view() {
        List<Community> communities = communityRepository.findAll();
        return communities;
    }

    @PostMapping("/createGroup")
    public ResponseEntity<Map<String, String>> createCommunity(@ModelAttribute Community community, @RequestParam("user") Long[] user) {
        System.out.println(user.length);
        communityService.createGroup(community, Arrays.asList(user));
        Map<String,String> response = new HashMap<>();
        response.put("message","Created successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ownerNames/{communityId}")
    public ResponseEntity<?> getOwnerNames(@PathVariable Long communityId) {
        try {
            List<String> ownerNames = communityService.getOwnerNamesByCommunityId(communityId);
            return ResponseEntity.ok(ownerNames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching owner names");
        }
    }

    @DeleteMapping("/delete/{communityId}")
    public ResponseEntity<?> deletedUser(@PathVariable("communityId") Long communityId){
        communityRepository.deleteById(communityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/user/{communityId}")
    public ResponseEntity<List<User>> getAllUsersByCommunity(@PathVariable("communityId")Long communityId,Model model){
        var community = communityService.getCommunityBy(communityId);
        System.out.println("DDDD"+community.getName());
        System.out.println("IDDIDI"+community.getId());
        List<User_Group> user_groups = user_groupService.findByCommunityId(communityId);
        System.out.println("USEGROUP"+user_groups.size());
        List<User> users = new ArrayList<>();
        for(User_Group user_group:user_groups){
            User user = userService.findById(user_group.getUser().getId());
            System.out.println("eeeeee"+user.getName());
            users.add(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/loginUserGroups")
    @ResponseBody
    public ResponseEntity<List<Community>> findAllGroupOfCurrentUser(){
        return ResponseEntity.status(HttpStatus.OK).body(communityService.getAllCommunityWithUserId());
    }


}
