package com.communityHubSystem.communityHub.impls;

import com.communityHubSystem.communityHub.models.User_Group;
import com.communityHubSystem.communityHub.repositories.User_GroupRepository;
import com.communityHubSystem.communityHub.services.User_GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class User_GroupImpl implements User_GroupService {

    private final User_GroupRepository user_groupRepository;

    @Override
    public List<User_Group> findByCommunityId(Long id) {
        return user_groupRepository.findByCommunityId(id);
    }
}
