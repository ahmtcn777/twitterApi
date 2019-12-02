package com.bilgeadam.services;

import com.bilgeadam.entity.Retweet;
import com.bilgeadam.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetweetService {
    @Autowired
    RetweetRepository retweetRepository;

    public List<Retweet> getRetweetsByName(String rtUsername)
    {
        return retweetRepository.findRetweetsByUsername(rtUsername);
    }
    public Retweet getRetweets(String username, Long tweedId)
    {
        return retweetRepository.findRetweetByUsernameAndAndTweetId(username, tweedId);
    }
    public boolean isRetweetedByYou(Retweet retweet)
    {
        return retweetRepository.existsByUsernameAndTweetId(retweet.getUsername(), retweet.getTweetId());
    }
    public void retweetTweet(Retweet retweet)
    {
        retweetRepository.save(retweet);
    }
    public void unRetweetTweet(Retweet retweet){
        retweetRepository.delete(retweet);
    }
}