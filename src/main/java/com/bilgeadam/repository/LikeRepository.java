package com.bilgeadam.repository;

import com.bilgeadam.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUsernameAndTweetId(@Param("username") String username,
                                       @Param("tweetId") Integer tweetId);

    Like findLikeByUsernameAndTweetId(@Param("username") String username,
                                      @Param("tweetId") Integer tweetId);

    List<Like> findLikesByUsername(@Param("username") String username);
}
