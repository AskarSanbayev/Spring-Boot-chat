package com.chat.cyber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_interests")
public class Interests {
    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", referencedColumnName = "record_id", nullable = false)
    @JsonIgnore
    private User user;

    private String activities;
    private String aboutInterests;
    private String favoriteMusic;
    private String favoriteMovies;
    private String favoriteShows;
    private String favoriteBooks;
    private String favoriteQuotes;
    private String aboutMe;
}
