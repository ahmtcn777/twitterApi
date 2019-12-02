package com.bilgeadam.controller;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.entity.Tweet;
import com.bilgeadam.model.PostModel;
import com.bilgeadam.services.CommentService;
import com.bilgeadam.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TweetController {
    @Autowired
    TweetService tweetService;
    @Autowired
    CommentService commentService;

    @RequestMapping(method = RequestMethod.GET,value = "/timeline/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostModel> getAllTweets(@PathVariable String username)
    {
        return tweetService.getAllTweetsForHomePage(username);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/profile/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostModel> getAllTweetsProfile(@PathVariable String username)
    {
        return tweetService.getAllTweetsForProfilePage(username);
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public PostModel  createTweet(@RequestBody Tweet tweet)
    {
        return tweetService.saveTweet(tweet);
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTweet(@RequestParam Long id, @RequestBody Tweet tweet)
    {
        tweetService.updateTweet(id, tweet);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTweet(@RequestParam Long id)
    {
        tweetService.deleteTweet(id);
        if(commentService.existsComment(id)){
            commentService.deleteComment(id);
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = "/{username}")
    public List<Tweet> getTweetByName(@PathVariable String username)
    {
        return tweetService.getTweets(username);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/status/{tweetId}")
    public PostModel getStatus(@PathVariable Long tweetId){
        return tweetService.getStatus(tweetId);
    }
}
