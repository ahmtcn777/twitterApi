package com.bilgeadam.controller;

import com.bilgeadam.entity.Like;
import com.bilgeadam.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@CrossOrigin
public class LikeController {
    @Autowired
    LikeService likeService;

    @RequestMapping(method = RequestMethod.GET,value = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Like> getLikes(@PathVariable String username)
    {
        return likeService.getLikes(username);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void like(@RequestBody Like like)
    {
        likeService.like(like);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/{username}/{tweetId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isLikedByYou(@PathVariable String username, @PathVariable int tweetId)
    {
        Like actualLike = likeService.getLike(username, tweetId);
        if(actualLike == null)
            return false;
        return true;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE)
    public void unlike(@RequestBody Like like)
    {
        Like actualLike = likeService.getLike(like.getUsername(), like.getTweetId());
        likeService.unlike(actualLike);

    }
}
