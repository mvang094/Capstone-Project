package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.UserDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.User;
import com.capstone.movieApp.entities.Watched_List;
import com.capstone.movieApp.entities.Watched_ListKey;
import com.capstone.movieApp.repositories.MovieRepo;
import com.capstone.movieApp.repositories.UserRepo;
import com.capstone.movieApp.repositories.WatchedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{ ;
    @Autowired
    MovieRepo movieRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    WatchedRepo watchedRepo;

    @Override
    public Optional<MovieDto> getMovieByName(String movieName){
      Optional<Movies> moviesOptional = movieRepo.findByTitle(movieName);
      //This is the functional style
        return moviesOptional.map(MovieDto::new);
        /* This is what the functional style is expanded out
        if(moviesOptional.isPresent()){
          return Optional.of(new MovieDto(moviesOptional.get()));
      }
      return Optional.empty();
         */
    };

    @Override
    public Optional<MovieDto> getMoviesById(Long movieId){
        Optional<Movies> movieOptional = movieRepo.findById(movieId);
        if(movieOptional.isPresent())
            return Optional.of(new MovieDto(movieOptional.get()));
        return Optional.empty();
    }

    @Override
    public HashSet<MovieDto> getHomeAll()
    {
        HashSet<MovieDto> homeArray = new HashSet<>();
        Long[] parameters = new Long[]{(long)2, (long)10, (long)15, (long)34, (long)43, (long)58,
                (long)36, (long)8};
        for(long elem : parameters){
            Optional<Movies> movieOptional = movieRepo.findById(elem);
            if(movieOptional.isPresent()) {
                homeArray.add(new MovieDto(movieOptional.get()));
            }
        }
        return homeArray;
    }

    @Override
    public void addMovieToWatchedList(Long movieId, Long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Movies> movieOptional = movieRepo.findById(movieId);
        if(userOptional.isPresent()&& movieOptional.isPresent()) {
            User user = userOptional.get();
            Movies movies = movieOptional.get();
            Watched_List newList = new Watched_List(user, movies);
            watchedRepo.saveAndFlush(newList);
        }
    }


    @Override
    public void addToInterestedList(Long userId, Long movieId){
        Optional<User> userOptional = userRepo.findById(userId);
        Optional<Movies> moviesOptional = movieRepo.findById(movieId);
        Watched_ListKey key = new Watched_ListKey(userId, movieId);
        Optional<Watched_List> list = watchedRepo.findById(key);

        if (!list.isPresent() && userOptional.isPresent() && moviesOptional.isPresent()){
            User user = userOptional.get();
            Movies movies = moviesOptional.get();

            movies.addInterestedUsers(user);
            user.addInterestedMovies(movies);
            userRepo.saveAndFlush(user);
        }
    }

    @Override
    public List<Movies> findGenre(String genre){
        List<Movies> genreList = movieRepo.findAllBygenre(genre);
        return genreList;
    }


}
