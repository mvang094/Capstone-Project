package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.Watched_ListDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.User;
import com.capstone.movieApp.entities.Watched_List;
import com.capstone.movieApp.entities.Watched_ListKey;
import com.capstone.movieApp.repositories.MovieRepo;
import com.capstone.movieApp.repositories.UserRepo;
import com.capstone.movieApp.repositories.WatchedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class Watched_ServiceImpl implements Watched_Service {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private WatchedRepo watchedRepo;

    public void addMovieToWatchedList(Long movieId, Long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Movies> moviesOptional = movieRepo.findById(movieId);
        System.out.println(userRepo.findById(userId));
        System.out.println(moviesOptional);
        if(userOptional.isPresent() && moviesOptional.isPresent()) {
            Watched_ListKey listKey = new Watched_ListKey(userId, movieId);
            User user = userOptional.get();
            Movies movies = moviesOptional.get();
            Watched_List watched_list = new Watched_List();
            watched_list.setWatch_id(listKey);
            watched_list.setUser(user);
            watched_list.setMovies(movies);
            watched_list.setWatched(true);
            System.out.println("Success!");
            watchedRepo.saveAndFlush(watched_list);
        }
    }

//    @Transactional
//    public void deleteFromWatchedList(Long userId, Long movieId){
//
//    }
//
//    @Transactional
//    public void updateWatchList(MovieDto movieDto, Long userId){
//
//    }
//
//    public List<Watched_ListDto> getWatchedList(Long userId){
//
//    }
//
//    //If you want to look at the details again
//    public Optional<Watched_ListDto> getWatchEntry(Long noteId){
//
//    }

}
