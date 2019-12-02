package com.bilgeadam.controller;

import com.bilgeadam.entity.Retweet;
import com.bilgeadam.entity.Tweet;
import com.bilgeadam.services.RetweetService;
import com.bilgeadam.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/retweet")
@CrossOrigin
public class RetweetController {
    @Autowired
    RetweetService retweetService;

    @Autowired
    TweetService tweetService;

    @RequestMapping(method = RequestMethod.GET,value = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tweet> getRetweets(@PathVariable String username)
    {
        List<Tweet> tweetList = new ArrayList<>();
        for(Retweet retweet : retweetService.getRetweetsByName(username))
        {
            tweetList.add(tweetService.getTweetById(retweet.getTweetId()));
        }

        return tweetList;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void retweet(@RequestBody Retweet retweet)
    {
        retweetService.retweetTweet(retweet);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unretweet(@RequestBody Retweet retweet)
    {
        Retweet actualRetweet = retweetService.getRetweets(retweet.getUsername(), retweet.getTweetId());
        retweetService.unRetweetTweet(actualRetweet);

    }
    @RequestMapping(method = RequestMethod.GET,value = "/{username}/{tweetId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isRetweetedByYou(@PathVariable String username, @PathVariable Long tweetId)
    {
        Retweet actualRetweet = retweetService.getRetweets(username, tweetId);
        if(actualRetweet == null)
            return false;
        return true;
    }
}
