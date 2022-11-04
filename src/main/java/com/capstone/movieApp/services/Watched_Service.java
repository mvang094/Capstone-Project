package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.Watched_ListDto;
import com.capstone.movieApp.entities.Watched_List;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface Watched_Service {
    void addMovieToWatchedList(Long movieId, Long userId);

    @Transactional
    void deleteFromWatchedList(Long movieId);

//    @Transactional
//    //To add review
//    void updateWatchList(Long movieId);

    //Return the user's watched list
    List<Watched_ListDto> getWatchedList(Long userId);

    //If you want to look at the details again
//    Optional<Watched_ListDto> getWatchEntry(Long noteId);

}
