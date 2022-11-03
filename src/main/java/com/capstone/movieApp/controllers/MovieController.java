package com.capstone.movieApp.controllers;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.services.MovieService;
import com.capstone.movieApp.services.Watched_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/capstone")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private Watched_Service watchedService;
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

    @GetMapping("/action")
    public List<Movies> getActionMovies(){
        return movieService.findGenre("Action");
    }
    @GetMapping("/adventureHome")
    public List<Movies> getAdventureMovies(){
        return movieService.findGenre("Adventure");
    }
    @GetMapping("/comedy")
    public List<Movies> getComedyMovies(){
        return movieService.findGenre("Comedy");
    }
    @GetMapping("/horror")
    public List<Movies> getHorrorMovies(){
        return movieService.findGenre("Horror");
    }
    @GetMapping("/mystery")
    public List<Movies> getMysteryMovies(){
        return movieService.findGenre("Mystery");
    }
    @GetMapping("/romance")
    public List<Movies> getRomanceMovies(){
        return movieService.findGenre("Romance");
    }

    @PostMapping("/watched/{userId}/{movieId}")
    public void addWatched (@PathVariable Long movieId, @PathVariable Long userId){
        watchedService.addMovieToWatchedList(movieId, userId);
    }
}
