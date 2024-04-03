package com.communityHubSystem.communityHub.repositories;

import com.communityHubSystem.communityHub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByStaffId(String staffId);
}
