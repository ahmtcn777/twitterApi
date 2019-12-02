package com.bilgeadam.repository;

import com.bilgeadam.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByTweetId(Long tweetId);
    List<Comment> findCommentsByUsername(String username);
    boolean existsByCommentId(Long id);
}