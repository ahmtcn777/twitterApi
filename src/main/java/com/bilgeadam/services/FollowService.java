package com.bilgeadam.services;

import com.bilgeadam.entity.Follow;
import com.bilgeadam.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    @Autowired
    FollowRepository followRepository;

    public List<Follow> getAllFollowings(String username)
    {
        return followRepository.findFollowsByUsername(username);
    }
    public void unFollow(Follow follow)
    {
        String username = follow.getUsername();
        String following = follow.getFollowingUsername();
        Follow actualFollow = followRepository.findFollowByUsernameAndFollowingUsername(username, following);
        followRepository.delete(actualFollow);
    }
    public boolean isFollowing(String username, String followingUsername)
    {
        return followRepository.existsFollowByUsernameAndFollowingUsername(username, followingUsername);
    }
    public void follow(Follow follow)
    {
        followRepository.save(follow);
    }
    public int getFollowersCount(String username){
        return followRepository.countFollowsByFollowingUsername(username);
    }
}
