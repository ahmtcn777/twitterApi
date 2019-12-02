package com.bilgeadam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",uniqueConstraints={
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"username"})
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize=1)
    private Long id;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "person", referencedColumnName = "username")
    private List<Follow> person = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<Retweet> retweet = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<Like> like = new ArrayList<>();
}