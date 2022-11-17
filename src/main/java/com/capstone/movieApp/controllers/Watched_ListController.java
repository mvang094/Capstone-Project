package com.capstone.movieApp.controllers;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.Watched_ListDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.Watched_ListKey;
import com.capstone.movieApp.services.MovieService;
import com.capstone.movieApp.services.Watched_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/capstone/watched")
public class Watched_ListController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private Watched_Service watchedService;
    @GetMapping("/{userId}")
    public Set<MovieDto> getUserWatchedList(@PathVariable Long userId){
        return watchedService.getWatchedList(userId);
    }

    @GetMapping("/{userId}/{movieId}")
    public MovieDto getWatchedMovieItem(@PathVariable Long userId, @PathVariable Long movieId){
        return watchedService.getWatchedMovies(userId, movieId);
    }

    @GetMapping("/rating/{userId}/{movieId}")
    public Watched_ListDto getMovieRating(@PathVariable Long userId, @PathVariable Long movieId){
        return watchedService.getRating(userId, movieId);
    }

    @PostMapping("/{userId}/{movieId}")
    public void addWatched(@PathVariable Long movieId, @PathVariable Long userId){
        watchedService.addMovieToWatchedList(movieId, userId);
    }

    @DeleteMapping("/{userId}/{movieId}")
    public void deleteMovies(@PathVariable Long movieId, @PathVariable Long userId){
        watchedService.deleteFromWatchedList(movieId, userId);
    }

    @PutMapping("/review/{userId}/{movieId}")
    public void addMovieReview(@RequestBody Watched_ListDto watched_listDto, @PathVariable Long userId, @PathVariable Long movieId){
        watchedService.addReview(watched_listDto, userId, movieId);
    }
}
