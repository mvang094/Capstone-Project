package com.capstone.movieApp.controllers;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/capstone")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @GetMapping("/homepage")
    public MovieDto[] getHomePageMovies()
    {
        HashSet<MovieDto> returnMovies = movieService.getHomeAll();
        MovieDto[] showMovies = returnMovies.toArray(new MovieDto[returnMovies.size()]);
        return showMovies;
    }

    @GetMapping("/{movieId}")
    public Optional<MovieDto> getMoviesById(@PathVariable Long movieId)
    {
        return movieService.getMoviesById(movieId);
    }


}
