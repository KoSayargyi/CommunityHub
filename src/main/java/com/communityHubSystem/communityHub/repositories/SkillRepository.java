package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.Skill;
import com.communityHubSystem.communityHub.models.User_Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SkillRepository extends JpaRepository<Skill,Long> , JpaSpecificationExecutor<User_Skill> {
}
