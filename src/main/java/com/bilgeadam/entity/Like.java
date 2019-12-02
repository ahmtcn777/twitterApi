package com.bilgeadam.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tweet_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_generator")
    @SequenceGenerator(name="like_generator", sequenceName = "like_seq", allocationSize=1)
    @Column(name = "id")
    private Long likeId;

    @Column(name = "username")
    private String username;

    @Column(name = "tweet_id")
    private int tweetId;
}
