package com.capstone.movieApp.controllers;

import com.capstone.movieApp.dtos.Casting_ListDto;
import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.Watched_ListDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.Watched_List;
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

    @GetMapping("/{id}")
    public Optional<MovieDto> getMoviesById(@PathVariable Long id)
    {
        return movieService.getMoviesById(id);
    }

    @GetMapping("/movie/{name}")
    public Optional<MovieDto> getMoviesByName(@PathVariable String name)
    {
        return movieService.getMovieByName(name);
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

    @GetMapping("/reviews/{movieId}")
    public Set<Watched_ListDto> getMovieReviews(@PathVariable Long movieId){
        return movieService.getReviews(movieId);
    }
}
