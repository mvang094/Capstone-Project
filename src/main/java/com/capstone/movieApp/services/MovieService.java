package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.UserDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.User;
import com.capstone.movieApp.entities.Watched_ListKey;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface MovieService {

    //This is when a user clicks on a movie to see the details
    Optional<MovieDto> getMoviesById(Long movieId);

    //This is when the search function is used
    Optional<MovieDto> getMovieByName(String movieName);

//    List<> addMovies();
    //This is to return set of movies to the home screen; No duplications
    HashSet<MovieDto> getHomeAll();

    //Adds movies to the watchlist by finding the user's watchlist, then setting the movieDto
    //body to the user's watchlist account. The watchlist should be unique to each user?
    void addMovieToWatchedList(Long movieId, Long userid); //Need ids from to add to Watched_ListKey listKey);


    List<Movies> findGenre(String genre);

    void addToInterestedList(Long userId, Long movieId);

}
