package com.capstone.movieApp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//creates a key that becomes the primary key for the intermediate table Watched_List
@Embeddable
public class Watched_ListKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "movie_id")
    Long movieId;

    private Watched_ListKey(){}

    public Watched_ListKey(Long userId, Long movieId){
        this.userId = userId;
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Watched_ListKey that = (Watched_ListKey) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }

}
