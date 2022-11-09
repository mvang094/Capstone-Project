package com.capstone.movieApp.controllers;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.services.MovieService;
import com.capstone.movieApp.services.Watched_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/capstone/interested")
public class InterestedController {

    @Autowired
    MovieService movieService;
    @Autowired
    Watched_Service watchedService;

    @PostMapping("/{userId}/{movieId}")
    public void addInterested(@PathVariable Long userId, @PathVariable Long movieId){
        movieService.addToInterestedList(userId, movieId);
    }

    @PostMapping("/watched/{userId}/{movieId}")
    public void addWatched(@PathVariable Long userId, @PathVariable Long movieId){
        watchedService.addToWatchedFromInterested(userId, movieId);
    }

    @GetMapping("/{userId}")
    public Set<MovieDto> getMovies(@PathVariable Long userId){
        return movieService.getInterestedList(userId);
    }

    @DeleteMapping("/{userId}/{movieId}")
    public void deleteInterested(@PathVariable Long userId, @PathVariable Long movieId){
        movieService.deleteFromInterestedList(userId, movieId);
    }

}
