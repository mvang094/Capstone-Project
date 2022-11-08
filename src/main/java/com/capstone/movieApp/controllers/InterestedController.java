package com.capstone.movieApp.controllers;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/capstone/interested")
public class InterestedController {

    @Autowired
    MovieService movieService;

    @PostMapping("/{userId}/{movieId}")
    public void addInterested(@PathVariable Long userId, @PathVariable Long movieId){
        movieService.addToInterestedList(userId, movieId);
    }

    @GetMapping
    public Set<MovieDto> getMovies(@PathVariable Long userId){
        movieService.getInterestedList(userId);
    }

}
