package com.bilgeadam.services;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.entity.Tweet;
import com.bilgeadam.entity.User;
import com.bilgeadam.model.PostModel;
import com.bilgeadam.repository.CommentRepository;
import com.bilgeadam.repository.TweetRepository;
import com.bilgeadam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TweetService tweetService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    public PostModel comment(Comment comment)
    {
        Tweet tweet = new Tweet();
        tweet.setContent(comment.getContent());
        tweet.setUsername(comment.getUsername());
        PostModel postModel =  tweetService.saveTweet(tweet);
        comment.setCommentId(postModel.getTweetId());
        commentRepository.save(comment);
        return postModel;
    }

    public List<PostModel> getComments(Long tweetId)
    {
        List<PostModel> postModels = new ArrayList<>();
        for(Comment comment : commentRepository.findCommentsByTweetId(tweetId))
        {
            User user = userRepository.findByUsername(comment.getUsername());
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
            postModel.setCommentCount(tweet.getComments().size());
            postModel.setRtCount(tweet.getRetweets().size());
            postModels.add(postModel);
        }

        return postModels;
    }

    public List<Comment> getUserComments(String username)
    {
        return commentRepository.findCommentsByUsername(username);
    }

    public boolean existsComment(Long id){
        return commentRepository.existsByCommentId(id);
    }

    public void deleteComment(Long id){
        commentRepository.delete(id);
    }

}
