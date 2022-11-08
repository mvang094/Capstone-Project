package com.capstone.movieApp.dtos;

import com.capstone.movieApp.entities.Casting_List;
import com.capstone.movieApp.entities.Movies;

public class CastDetailsDto {

    private Casting_List cast;
    private Movies movies;

    public CastDetailsDto(){};
    public CastDetailsDto(Casting_List cast, Movies movies){
        this.cast = cast;
        this.movies = movies;
    }

    public void setCast(Casting_List cast){
        this.cast = cast;
    }

    public Casting_List getCast(){
        return cast;
    }

    public void setMovies(Movies movies){
        this.movies = movies;
    }

    public Movies getMovies(){
        return movies;
    }
}
