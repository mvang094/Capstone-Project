package com.capstone.movieApp.entities;

import com.capstone.movieApp.dtos.Watched_ListDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "watched_list")
public class Watched_List {

    @EmbeddedId //the primary key for the intermediate table
    private Watched_ListKey watch_id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    private Movies movies;

    @Column(name = "movie_watched")
    private Boolean watched;

    @Column(name = "movie_rating")
    private Integer rating;

    @Column(name = "movie_review")
    private String review;

    public Watched_List (Watched_ListDto watchDto){
        if (watchDto.getRating() != null)
            this.rating = watchDto.getRating();
        if(watchDto.getWatched() != null)
            this.watched = watchDto.getWatched();
        if(watchDto.getReview() != null)
            this.review = watchDto.getReview();
    }

    public Watched_ListKey getWatch_id() {
        return watch_id;
    }

    public void setWatch_id(Watched_ListKey watch_id) {
        this.watch_id = watch_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
