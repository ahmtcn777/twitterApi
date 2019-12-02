package com.bilgeadam.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweet_generator")
    @SequenceGenerator(name="tweet_generator", sequenceName = "tweet_seq", allocationSize=1)
    @Column(name = "id")
    private Long tweetId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tweet_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @OneToMany
    @JoinColumn(name = "tweet_id", referencedColumnName = "id")
    private List<Retweet> retweets = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "tweet_id", referencedColumnName = "id")
    private List<Like> likes = new ArrayList<>();

    @Column(name = "hashtag")
    private String hashtag;

    @OneToMany
    @JoinColumn(name = "tweet_id", referencedColumnName = "id")
    private List<Comment> comments = new ArrayList<>();

}
