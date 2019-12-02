package com.bilgeadam.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comment_table")
public class Comment {
    @Id
    @Column(name = "id")
    private Long commentId;

    @Column(name = "username")
    private String username;

    @Column(name = "tweet_id")
    private Long tweetId;

    @Column(name = "content")
    private String content;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_date")
    private Date commentDate;
}