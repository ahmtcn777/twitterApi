package com.bilgeadam.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Retweet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "retweet_generator")
    @SequenceGenerator(name="retweet_generator", sequenceName = "retweet_seq", allocationSize=1)
    @Column(name = "id")
    private Long retweetId;

    @Column(name = "username")
    private String username;

    @Column(name = "tweet_id")
    private Long tweetId;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rt_date")
    private Date rtDate;
}
