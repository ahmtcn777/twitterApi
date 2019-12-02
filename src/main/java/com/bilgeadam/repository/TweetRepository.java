package com.bilgeadam.repository;

import com.bilgeadam.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findTweetsByUsernameOrderByCreateDateDesc(@Param("username") String username);
    Tweet findTweetByTweetId(@Param("id") Long id);
}
