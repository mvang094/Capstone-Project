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
    public void addMovieToWatchedList(Movies movies, User user){
        Optional<User> userOptional = userRepo.findById(user.getId());
//        Movies newMovie = new Movies(movieDto); //Creates a new instance of the Movies class with the moviesDto as argument
        if(userOptional.isPresent()) {
            Watched_List newList = new Watched_List(user, movies);
            watchedRepo.saveAndFlush(newList);
        }
//        userOptional.ifPresent(watched -> movies.setWatched(watched));
        /*
            :: - method reference operator - behaves just like Lamda expressions
            <Class name>::<method name>
            In this example, it used the instance method which is
            (objectOfClass::methodName)
            Same as:
            userOptional.ifPresent( note -> Note.setUser(note))
         */
    }

    @Override
    public void deleteMovie(Movies movies) {
        Optional<Watched_List> watchOptional = watchedRepo.findBymovies(movies);
        watchOptional.ifPresent(movie -> watchedRepo.delete(movie));
    }

    @Override
    public List<Movies> findGenre(String genre){
        List<Movies> genreList = movieRepo.findAllBygenre(genre);
        return genreList;
    }


}
