package com.bilgeadam.controller;

import com.bilgeadam.entity.Comment;
import com.bilgeadam.model.PostModel;
import com.bilgeadam.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostModel comment(@RequestBody Comment comment)
    {
        return commentService.comment(comment);
    }

    @GetMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostModel> getComments(@PathVariable Long tweetId)
    {
        return commentService.getComments(tweetId);
    }

    @GetMapping("/user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getUsersComments(@PathVariable String username)
    {
        return commentService.getUserComments(username);
    }
}