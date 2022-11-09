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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Watched_ServiceImpl implements Watched_Service {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private WatchedRepo watchedRepo;

    @Override
    public void addMovieToWatchedList(Long movieId, Long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Movies> moviesOptional = movieRepo.findById(movieId);
        if(userOptional.isPresent() && moviesOptional.isPresent()) {
            User user = userOptional.get();
            Movies movies = moviesOptional.get();
            Watched_List watched_list = new Watched_List(user, movies);
            watched_list.setMovies(movies);
            watched_list.setUser(user);
            user.addWatchedList(watched_list);
            movies.addWatchedList(watched_list);

            watchedRepo.saveAndFlush(watched_list);
        }
    }

    @Override
    @Transactional
    public void deleteFromWatchedList(Long movieId, Long userId) {
        Watched_ListKey key = new Watched_ListKey(userId, movieId);
        Optional<Watched_List> watchOptional = watchedRepo.findById(key);
        watchOptional.ifPresent(watch -> watchedRepo.delete(watch));
    }
    @Transactional
    @Override
    public void addReview(Watched_ListDto watched_listDto, Long userId, Long movieId){
        Watched_ListKey key = new Watched_ListKey(userId, movieId);
        Optional<Watched_List> watchedOptional = watchedRepo.findById(key);

        watchedOptional.ifPresent(review -> {
            review.setReview(watched_listDto.getReview());
            review.setRating((Integer)watched_listDto.getRating());
            watchedRepo.saveAndFlush(review);
        });
    }

    @Override
    public Set<MovieDto> getWatchedList(Long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isPresent())
        {
            List<Watched_List> watchedList = watchedRepo.findAllByUserEquals(userOptional.get());
            ArrayList<Movies> movies = new ArrayList<>();
            watchedList.forEach(elem -> movies.add(elem.getMovies()));
            return movies.stream().map(watched -> new MovieDto(watched)).collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    @Override
    public MovieDto getWatchedMovies(Long userId, Long movieId){
        Watched_ListKey key = new Watched_ListKey(userId, movieId);
        Optional<Watched_List> watchedOptional = watchedRepo.findById(key);
        Watched_List watched_list = watchedOptional.get();
        Movies movies = watched_list.getMovies();
        MovieDto movieDto = new MovieDto((movies));
        return movieDto;
    }

    @Override
    public Watched_ListDto getRating(Long userId, Long movieId){
        Watched_ListKey key = new Watched_ListKey(userId, movieId);
        Optional<Watched_List> watchedOptional = watchedRepo.findById(key);
        Watched_List watched_list = watchedOptional.get();
        Watched_ListDto watchedDto = new Watched_ListDto(watched_list);
        return watchedDto;
    }

    @Override
    public void addToWatchedFromInterested(Long userId, Long movieId) {
//        Watched_ListKey key = new Watched_ListKey(userId, movieId);
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Movies> moviesOptional = movieRepo.findById(movieId);
//        addMovieToWatchedList(userId, movieId);
        if (userOptional.isPresent() && moviesOptional.isPresent()) {
            User user = userOptional.get();
            Movies movies = moviesOptional.get();
            Watched_List watched_list = new Watched_List(user, movies);
            watched_list.setMovies(movies);
            watched_list.setUser(user);

            watchedRepo.saveAndFlush(watched_list);
        }
    }

}
