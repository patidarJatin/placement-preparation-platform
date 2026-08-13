package com.jatinpatidar.placementpro.repository;

import com.jatinpatidar.placementpro.entity.User;
import com.jatinpatidar.placementpro.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    Optional<UserProfile> findById(User user);
}
