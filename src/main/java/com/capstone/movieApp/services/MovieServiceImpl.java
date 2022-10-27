package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.repositories.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{ ;
    @Autowired
    MovieRepo movieRepo;

    @Override
    public Optional<MovieDto> getMoviesById(Long movieId){
        Optional<Movies> movieOptional = movieRepo.findById(movieId);
        if(movieOptional.isPresent())
            return Optional.of(new MovieDto(movieOptional.get()));
        return Optional.empty();
    }

    @Override
    public ArrayList<MovieDto> getHomeAll(Long one, Long two, Long three,
                                           Long four, Long five, Long six,
                                           Long seven, Long eight)
    {
        ArrayList<MovieDto> homeArray = new ArrayList<>();
        Long[] parameters = new Long[]{one, two, three, four, five, six,
                seven, eight};
        for(int i = 0; i < parameters.length; i++){
            Optional<Movies> movieOptional = movieRepo.findById(parameters[i]);
            if(movieOptional.isPresent()) {
                homeArray.add(new MovieDto(movieOptional.get()));
            }
        }
        return homeArray;

    }
}
