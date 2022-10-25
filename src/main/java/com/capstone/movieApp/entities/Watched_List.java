package com.capstone.movieApp.entities;

import javax.persistence.*;

@Entity
@Table(name = "watched_list")
public class Watched_List {

    @EmbeddedId //the primary key for the intermediate table
    Watched_ListKey watch_id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    Movies movies;

    @Column(name = "watched?")
    boolean watched;
}
