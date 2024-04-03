package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.User_Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface User_SkillRepository extends JpaRepository<User_Skill,Long>  {
}
