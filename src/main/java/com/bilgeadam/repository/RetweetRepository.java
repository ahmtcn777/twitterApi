package com.bilgeadam.repository;

import com.bilgeadam.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    boolean existsByUsernameAndTweetId(@Param("username") String username,
                                       @Param("tweetId") Long tweetId);
    List<Retweet> findRetweetsByUsername(@Param("username") String username);
    Retweet findRetweetByUsernameAndAndTweetId(@Param("username") String username,
                                               @Param("tweetId") Long tweetId);

}