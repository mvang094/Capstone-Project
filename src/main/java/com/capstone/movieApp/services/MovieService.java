package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.MovieDto;

import java.util.ArrayList;
import java.util.Optional;

public interface MovieService {
    Optional<MovieDto> getMoviesById(Long movieId);
    ArrayList<MovieDto> getHomeAll(Long one, Long two, Long three, Long four,
                                    Long five, Long six, Long seven, Long eight);
}
