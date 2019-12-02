package com.bilgeadam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "follow_generator")
    @SequenceGenerator(name = "follow_generator", sequenceName = "follow_seq", allocationSize = 1)
    private Long id;

    @Column(name = "person", nullable = false)
    private String username;

    @Column(name = "follows", nullable = false)
    private String followingUsername;
}
