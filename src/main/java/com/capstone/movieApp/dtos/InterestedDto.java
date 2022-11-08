package com.capstone.movieApp.dtos;

import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.User;

public class InterestedDto {

    private Movies movies;

    private User user;

    public InterestedDto(){};
    public InterestedDto(User user, Movies movies){
        this.user = user;
        this.movies = movies;
    }

    public void setMovies(Movies movies){
        this.movies = movies;
    }

    public Movies getMovies(){
        return movies;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

}
