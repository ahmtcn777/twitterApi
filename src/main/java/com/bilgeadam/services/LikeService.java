package com.bilgeadam.services;

import com.bilgeadam.entity.Like;
import com.bilgeadam.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    public void like(Like like)
    {
        likeRepository.save(like);
    }
    public void unlike(Like like)
    {
        likeRepository.delete(like);
    }
    public boolean isLiked(Like like)
    {
        return likeRepository.existsByUsernameAndTweetId(like.getUsername(), like.getTweetId());
    }
    public Like getLike(String username, int id)
    {
        return likeRepository.findLikeByUsernameAndTweetId(username, id);
    }
    public List<Like> getLikes(String username){
        return likeRepository.findLikesByUsername(username);
    }
}