package com.bilgeadam.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserModel {
    private String username;
    private String firstName;
    private String lastName;
    private int tweetCount;
    private int followers;
    private int followings;

}
