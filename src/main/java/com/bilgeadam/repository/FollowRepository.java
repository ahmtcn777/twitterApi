package com.bilgeadam.repository;


import com.bilgeadam.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, String> {
    List<Follow> findFollowsByUsername(String username);
    Follow findFollowByUsernameAndFollowingUsername(String username, String fallowingName);
    int countFollowsByFollowingUsername(String username);
    boolean existsFollowByUsernameAndFollowingUsername(String username, String fallowingName);
}
