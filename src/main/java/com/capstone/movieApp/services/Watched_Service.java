package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.Watched_ListDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.Watched_List;
import com.capstone.movieApp.entities.Watched_ListKey;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Watched_Service {
    void addMovieToWatchedList(Long movieId, Long userId);

    @Transactional
    void deleteFromWatchedList(Long movieId, Long userId);

    @Transactional
    //To add review
    void addReview(Watched_ListDto watched, Long userId, Long movieId);

    //Return the user's watched list
    public Set<MovieDto> getWatchedList(Long userId);

    //The above only returns the list with watched_id. It does not
    //have the movie object. The following method will hopefully show the movies
    MovieDto getWatchedMovies(Long userId, Long movieId);

    //If you want to look at the details again
//    Optional<Watched_ListDto> getWatchEntry(Long noteId);

    Watched_ListDto getRating(Long userId, Long movieID);

}
