//package com.capstone.movieApp.controllers;
//
//import com.capstone.movieApp.dtos.MovieDto;
//import com.capstone.movieApp.entities.Movies;
//import com.capstone.movieApp.services.MovieService;
//import com.capstone.movieApp.services.Watched_Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/capstone/watched")
//public class Watched_ListController {
//    @Autowired
//    private MovieService movieService;
//
//    @Autowired
//    private Watched_Service watchedService;
//    @PostMapping("/{userId}")
//    public void addWatched (@RequestBody MovieDto movieDto, @PathVariable Long userId){
//        watchedService.addMovieToWatchedList(movieDto, userId);
//    }
//}
