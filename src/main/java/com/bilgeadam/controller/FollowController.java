package com.bilgeadam.controller;

import com.bilgeadam.entity.Follow;
import com.bilgeadam.entity.User;
import com.bilgeadam.services.FollowService;
import com.bilgeadam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/follow")
@CrossOrigin
public class FollowController {
    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET,value = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getFollowingsByUsername(@PathVariable String username)
    {
        List<User> users = new ArrayList<>();
        for(Follow user : followService.getAllFollowings(username) ) {
            User following = userService.getUserByUserName(user.getFollowingUsername());
            users.add(following);
        }
        return users;
    }
    @RequestMapping(method = RequestMethod.GET,value = "/{username}/{followingUsername}")
    @ResponseStatus(HttpStatus.OK)
    public boolean getFollowRelation(@PathVariable String username, @PathVariable String followingUsername)
    {
        return followService.isFollowing(username, followingUsername);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void follow(@RequestBody Follow follow)
    {
        followService.follow(follow);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unFollow(@RequestBody Follow follow)
    {
        followService.unFollow(follow);
    }


}