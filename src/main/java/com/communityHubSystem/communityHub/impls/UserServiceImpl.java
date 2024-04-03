package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.models.*;
import com.communityHubSystem.communityHub.repositories.UserRepository;
import com.communityHubSystem.communityHub.services.UserService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public User updateUserData(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchMethod(Long id,
                                   Long doorLogNum,
                                   String staffId,
                                   String chatRoomName,
                                   String name,
                                   String gender,
                                   String email,
                                   String phone,
                                   String role,
                                   String groupName,
                                   String postDescription,
                                   String team,
                                   String division,
                                   String department,
                                   boolean isActive,
                                   List<String> hobbyNameList,
                                   List<String> policyRuleList,
                                   List<String> skillNameList) {

        var specifications = new ArrayList<Specification<User>>();
        if(id!=null){
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"),id));
        }

        if(doorLogNum!=null){
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("doorLogNum"),doorLogNum));
        }

        if(StringUtils.hasLength(staffId)){
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("staffId"),staffId));
        }

        if(StringUtils.hasLength(chatRoomName)){
            specifications.add(getFromChatRoomName(name));
        }

        if(StringUtils.hasLength(name)){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("name")),"%".concat(name.toLowerCase()).concat("%")));
        }

        if(StringUtils.hasLength(gender)){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder
                            .equal(criteriaBuilder.lower(root.get("gender")),gender.toLowerCase()));
        }

        if(StringUtils.hasLength(email)){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("email")),"%".concat(email.toLowerCase()).concat("%")));
        }

        if(StringUtils.hasLength(phone)){
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("phone"),phone));
        }

        if(StringUtils.hasLength(role)){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("role")),"%".concat(role.toLowerCase()).concat("%")));
        }

        if(StringUtils.hasLength(groupName)){
            specifications.add(getFromGroupsName(groupName));
        }

        if(StringUtils.hasLength(postDescription)){
            specifications.add(getFromPostDescription(postDescription));
        }

        if(StringUtils.hasLength(team)){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("team")),"%".concat(team.toLowerCase()).concat("%")));
        }

        if(StringUtils.hasLength(division)){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("division")),"%".concat(division.toLowerCase()).concat("%")));
        }

        if(StringUtils.hasLength(department)){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder
                            .like(criteriaBuilder.lower(root.get("department")),"%".concat(department.toLowerCase()).concat("%")));
        }

        if(isActive){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("isActive"),true));
        }

        if(!isActive){
            specifications.add((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("isActive"),false));
        }

        if(!hobbyNameList.isEmpty()){
            specifications.add((root, query, criteriaBuilder) -> {
                var hobby = new ArrayList<Predicate>();
                for(var h : hobbyNameList){
                    hobby.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("hobby")),"%".concat(h.toLowerCase()).concat("%")));
                }
                return criteriaBuilder.or(hobby.toArray(new Predicate[0]));
            });
        }

        if(!policyRuleList.isEmpty()){
            specifications.add(getFromPolicyRule(policyRuleList));
        }

        if(!skillNameList.isEmpty()){
            specifications.add(getUserFromSkill(skillNameList));
        }

        Specification<User> userSpec = Specification.where(null);
        for(var s : specifications){
            userSpec = userSpec.and(s);
        }

        return userRepository.findAll(userSpec);
    }

    public static Specification<User> getUserFromSkill(List<String > skillNameList){
        return (root, query, criteriaBuilder) -> {
            if(!skillNameList.isEmpty()){
                Join<User, User_Skill> userSkillJoin = root.join("user_skills");
                Join<User_Group,Skill> skillUserJoin = userSkillJoin.join("skill");
                var users = criteriaBuilder.disjunction();
                for(var s : skillNameList){
                    users = criteriaBuilder.or(users,criteriaBuilder.like(criteriaBuilder.lower(skillUserJoin.get("name")),"%".concat(s.toLowerCase()).concat("%")));
                }
                return users;
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<User> getFromChatRoomName(String name){
        return (root, query, criteriaBuilder) -> {
            if(StringUtils.hasLength(name)){
                Join<User, User_ChatRoom> userChatRoomJoin = root.join("user_chatRooms");
                Join<User_ChatRoom,ChatRoom> chatRoomUserJoin = userChatRoomJoin.join("chatRoom");
                return criteriaBuilder.like(criteriaBuilder.lower(chatRoomUserJoin.get("name")),"%".concat(name.toLowerCase()).concat("%"));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<User> getFromGroupsName(String name){
        return (root, query, criteriaBuilder) -> {
            if(StringUtils.hasLength(name)){
                Join<User,User_Group> userGroupJoin = root.join("user_groups");
                return criteriaBuilder.like(criteriaBuilder.lower(userGroupJoin.get("name")),"%".concat(name.toLowerCase()).concat("%"));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<User> getFromPolicyRule(List<String > policies){
        return (root, query, criteriaBuilder) -> {
            if(!policies.isEmpty()){
                Join<User,Policy> userPolicyJoin = root.join("policies");
                var rules = criteriaBuilder.disjunction();
                for(var policy : policies){
                    rules = criteriaBuilder.or(rules,criteriaBuilder.like(criteriaBuilder.lower(userPolicyJoin.get("rule")),"%".concat(policy).concat("%")));
                }
                return rules;
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<User> getFromPostDescription(String description){
        return (root, query, criteriaBuilder) -> {
            if(StringUtils.hasLength(description)){
               Join<User,Post> userPostJoin = root.join("posts");
               return criteriaBuilder.like(criteriaBuilder.lower(userPostJoin.get("description")),"%".concat(description).concat("%"));
           }
            return criteriaBuilder.conjunction();
       };
    }
}
