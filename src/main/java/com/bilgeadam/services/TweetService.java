package com.bilgeadam.services;

import com.bilgeadam.entity.*;
import com.bilgeadam.model.PostModel;
import com.bilgeadam.model.SortByDate;
import com.bilgeadam.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    RetweetRepository retweetRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentService commentService;
    List<PostModel> postList = new ArrayList<>();

    public List<PostModel> getAllTweetsForHomePage(String username) {

        List<PostModel> postList = new ArrayList<>();

        for (Follow Follow : followRepository.findFollowsByUsername(username)) {
            for(Comment comment : commentService.getUserComments(Follow.getFollowingUsername()))
            {
                User user = userRepository.findByUsername(Follow.getFollowingUsername());
                Tweet tw = tweetRepository.findTweetByTweetId(comment.getTweetId());
                Tweet tweet = tweetRepository.findTweetByTweetId(comment.getCommentId());

                PostModel postModel = new PostModel();
                postModel.setReplyTo(tw.getUsername());
                postModel.setContent(comment.getContent());
                postModel.setFirstName(user.getFirstname());
                postModel.setLastName(user.getLastname());
                postModel.setLikeCount(tweet.getLikes().size());
                postModel.setUpdateDate(comment.getCommentDate());
                postModel.setUsername(user.getUsername());
                postModel.setTweetId(comment.getCommentId());
                postModel.setRetweetedBy(null);
                postModel.setCommentCount(commentService.getComments(comment.getCommentId()).size());
                postModel.setRtCount(tweet.getRetweets().size());

                postList.add(postModel);
            }

            for (Tweet tweet : tweetRepository.findTweetsByUsernameOrderByCreateDateDesc(Follow.getFollowingUsername())) {
                User user = new User();
                user = userRepository.findByUsername(Follow.getFollowingUsername());
                PostModel postModel = new PostModel();
                postModel.setContent(tweet.getContent());
                postModel.setFirstName(user.getFirstname());
                postModel.setLastName(user.getLastname());
                postModel.setCreationDate(tweet.getCreateDate());
                postModel.setUsername(tweet.getUsername());
                postModel.setRetweetedBy(null);
                postModel.setTweetId(tweet.getTweetId());
                postModel.setUpdateDate(tweet.getUpdateDate());
                postModel.setRtCount(tweet.getRetweets().size());
                postModel.setLikeCount(tweet.getLikes().size());
                postModel.setCommentCount(tweet.getComments().size());
                if(!postList.contains(postModel))
                    postList.add(postModel);
            }
            for (Retweet retweet : retweetRepository.findRetweetsByUsername(Follow.getFollowingUsername())) {

                User user = new User();

                Tweet tw = tweetRepository.findTweetByTweetId(retweet.getTweetId());
                user = userRepository.findByUsername(tw.getUsername());
                if (!Follow.getUsername().equals(tw.getUsername())) {
                    PostModel postModel = new PostModel();
                    postModel.setContent(tw.getContent());
                    postModel.setFirstName(user.getFirstname());
                    postModel.setLastName(user.getLastname());
                    postModel.setLikeCount(tw.getLikes().size());
                    postModel.setCreationDate(tw.getCreateDate());
                    postModel.setUsername(tw.getUsername());
                    postModel.setTweetId(tw.getTweetId());
                    postModel.setRetweetedBy(retweet.getUsername());
                    postModel.setUpdateDate(retweet.getRtDate());
                    postModel.setRtCount(tw.getRetweets().size());
                    postModel.setCommentCount(tw.getComments().size());
                    postList.add(postModel);
                }
            }


        }
        Collections.sort(postList, new SortByDate());
        return postList;
    }

    public List<PostModel> getAllTweetsForProfilePage(String username) {
        List<PostModel> postModels = new ArrayList<>();
        for(Comment comment : commentService.getUserComments(username))
        {
            User user = userRepository.findByUsername(username);
            Tweet tw = tweetRepository.findTweetByTweetId(comment.getTweetId());
            Tweet tweet = tweetRepository.findTweetByTweetId(comment.getCommentId());

            PostModel postModel = new PostModel();
            postModel.setReplyTo(tw.getUsername());
            postModel.setContent(comment.getContent());
            postModel.setFirstName(user.getFirstname());
            postModel.setLastName(user.getLastname());
            postModel.setLikeCount(tweet.getLikes().size());
            postModel.setUpdateDate(comment.getCommentDate());
            postModel.setUsername(user.getUsername());
            postModel.setTweetId(comment.getCommentId());
            postModel.setRetweetedBy(null);
            postModel.setCommentCount(commentService.getComments(comment.getCommentId()).size());
            postModel.setRtCount(tweet.getRetweets().size());

            postModels.add(postModel);
        }
        for (Tweet tweet : tweetRepository.findTweetsByUsernameOrderByCreateDateDesc(username)) {

            User user = new User();
            user = userRepository.findByUsername(username);
            PostModel postModel = new PostModel();
            postModel.setContent(tweet.getContent());
            postModel.setFirstName(user.getFirstname());
            postModel.setLastName(user.getLastname());
            postModel.setLikeCount(tweet.getLikes().size());
            postModel.setCreationDate(tweet.getCreateDate());
            postModel.setUsername(tweet.getUsername());
            postModel.setTweetId(tweet.getTweetId());
            postModel.setRetweetedBy(null);
            postModel.setUpdateDate(tweet.getUpdateDate());
            postModel.setRtCount(tweet.getRetweets().size());
            postModel.setCommentCount(tweet.getComments().size());
            if(!postModels.contains(postModel))
                postModels.add(postModel);
        }
        for (Retweet retweet : retweetRepository.findRetweetsByUsername(username)) {
            User user = new User();
            Tweet tw = tweetRepository.findTweetByTweetId(retweet.getTweetId());
            user = userRepository.findByUsername(tw.getUsername());
            if (!username.equals(tw.getUsername())) {
                PostModel postModel = new PostModel();
                postModel.setContent(tw.getContent());
                postModel.setCreationDate(tw.getCreateDate());
                postModel.setFirstName(user.getFirstname());
                postModel.setLastName(user.getLastname());
                postModel.setLikeCount(tw.getLikes().size());
                postModel.setTweetId(tw.getTweetId());
                postModel.setUsername(tw.getUsername());
                postModel.setRetweetedBy(retweet.getUsername());
                postModel.setUpdateDate(retweet.getRtDate());
                postModel.setRtCount(tw.getRetweets().size());
                postModel.setCommentCount(tw.getComments().size());
                postModels.add(postModel);
            }


        }
        Collections.sort(postModels, new SortByDate());
        return postModels;

    }

    public List<Tweet> findAll() {
        return this.tweetRepository.findAll();
    }

    public PostModel saveTweet(Tweet tweet) {
        tweetRepository.save(tweet);
        Tweet tw = tweetRepository.findTweetByTweetId(tweet.getTweetId());
        PostModel postModel = new PostModel();
        User user = userRepository.findByUsername(tweet.getUsername());

        postModel.setContent(tw.getContent());
        postModel.setFirstName(user.getFirstname());
        postModel.setLastName(user.getLastname());
        postModel.setLikeCount(0);
        postModel.setCreationDate(tw.getCreateDate());
        postModel.setUsername(tw.getUsername());
        postModel.setTweetId(tw.getTweetId());
        postModel.setRetweetedBy(null);
        postModel.setUpdateDate(tw.getUpdateDate());
        postModel.setRtCount(0);

        return postModel;
    }


    public void updateTweet(Long id, Tweet tweet) {
        Tweet oldVersion = tweetRepository.findTweetByTweetId(id);
        oldVersion.setContent(tweet.getContent());

        tweetRepository.save(oldVersion);
    }

    public void deleteTweet(Long id) {
        tweetRepository.delete(tweetRepository.findTweetByTweetId(id));
    }

    public List<Tweet> getTweets(String username) {
        List<Tweet> tweetList = tweetRepository.findTweetsByUsernameOrderByCreateDateDesc(username);
        return tweetList;
    }

    public Tweet getTweetById(Long id) {
        return tweetRepository.findTweetByTweetId(id);
    }

    public PostModel getPostByTweetId(Long id) {
        Tweet tw = tweetRepository.findTweetByTweetId(id);
        PostModel postModel = new PostModel();
        User user = userRepository.findByUsername(tw.getUsername());

        postModel.setContent(tw.getContent());
        postModel.setFirstName(user.getFirstname());
        postModel.setLastName(user.getLastname());
        postModel.setLikeCount(tw.getLikes().size());
        postModel.setCreationDate(tw.getCreateDate());
        postModel.setUsername(tw.getUsername());
        postModel.setTweetId(tw.getTweetId());
        postModel.setRetweetedBy(null);
        postModel.setUpdateDate(tw.getUpdateDate());
        postModel.setRtCount(tw.getRetweets().size());
        postModel.setCommentCount(tw.getComments().size());

        return postModel;

    }


    public PostModel getStatus(Long id){
        Tweet tw = tweetRepository.findTweetByTweetId(id);
        PostModel postModel = new PostModel();
        User user = userRepository.findByUsername(tw.getUsername());

        postModel.setContent(tw.getContent());
        postModel.setFirstName(user.getFirstname());
        postModel.setLastName(user.getLastname());
        postModel.setLikeCount(tw.getLikes().size());
        postModel.setCreationDate(tw.getCreateDate());
        postModel.setUsername(tw.getUsername());
        postModel.setTweetId(tw.getTweetId());
        postModel.setRetweetedBy(null);
        postModel.setUpdateDate(tw.getUpdateDate());
        postModel.setRtCount(tw.getRetweets().size());
        postModel.setCommentCount(tw.getComments().size());
        return postModel;
    }
}
