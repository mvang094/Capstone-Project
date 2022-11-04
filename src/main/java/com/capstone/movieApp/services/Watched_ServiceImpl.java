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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(userOptional.isPresent() && moviesOptional.isPresent()) {
            User user = userOptional.get();
            Movies movies = moviesOptional.get();
            Watched_List watched_list = new Watched_List(user, movies);

            watchedRepo.saveAndFlush(watched_list);
        }
    }

    @Transactional
    public void deleteFromWatchedList(Long movieId) {

        Movies newMovie = movieRepo.findById(movieId).get();
        Optional<Watched_List> watchedOptional = watchedRepo.findBymovies(newMovie);
        watchedOptional.ifPresent(watched -> watchedRepo.delete(watched));
    }
//    @Transactional
//    public void updateWatchList(Long movieId){
//        Optional<Watched_List> watchedOptional = watchedRepo.findById(movieId);
//
//        watchedOptional.ifPresent(review -> {
//            Watched_List watched_list = watchedOptional.get();
//
//        });
//    }

    public List<Watched_ListDto> getWatchedList(Long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isPresent())
        {
            List<Watched_List> watchedList = watchedRepo.findAllByUserEquals(userOptional.get());
            return watchedList.stream().map(watched -> new Watched_ListDto(watched)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

//    public Optional<Watched_ListDto> getWatchEntry(Long noteId){

//    }

}
