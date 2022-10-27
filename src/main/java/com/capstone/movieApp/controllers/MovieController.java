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
    public ArrayList<MovieDto> getHomePageMovies()
    {
        return movieService.getHomeAll((long)2, (long)10, (long)15, (long)34,
                (long)43, (long)58, (long)36, (long)7);
    }


}
