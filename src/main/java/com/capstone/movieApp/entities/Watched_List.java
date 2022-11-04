package com.capstone.movieApp.entities;

import com.capstone.movieApp.dtos.Watched_ListDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "watched_list")
@AllArgsConstructor
@NoArgsConstructor
public class Watched_List {

    @EmbeddedId //the primary key for the intermediate table
    private Watched_ListKey watch_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public Watched_List(User user, Movies movies){
        this.user = user;
        this.movies = movies;
        this.watch_id = new Watched_ListKey(user.getId(), movies.getMovie_id());
        this.watched = true;
    }

    public Watched_ListKey getWatch_id() {
        return watch_id;
    }

    public void setWatch_id(Watched_ListKey watch_id) {
        this.watch_id = watch_id;
    }

    @Transient
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Transient
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Watched_List that = (Watched_List) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(movies, that.movies);
    }
    @Override
    public int hashCode() {
        return Objects.hash(user, user);
    }
}
