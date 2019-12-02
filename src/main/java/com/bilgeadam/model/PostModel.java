package com.bilgeadam.model;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class PostModel {

    private String replyTo;

    private  String retweetedBy;

    private String firstName;

    private String lastName;

    private Long tweetId;

    private String username;

    private String content;

    private Date creationDate;

    private Date updateDate;

    private int rtCount;

    private int likeCount;

    private int commentCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostModel postModel = (PostModel) o;
        return tweetId == postModel.tweetId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId);
    }
}