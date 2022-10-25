package com.capstone.movieApp.dtos;

import java.io.Serializable;
import java.util.*;

import com.capstone.movieApp.entities.Movies;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto implements Serializable {

    private Long movie_id;
    private Long movie_year;
    private Set<Watched_ListDto> watched_listDto = new HashSet<>();
    private String movie_title, movie_genre, movie_maturity, movie_keywordone,
            movie_keywordtwo, movie_image, movie_trailer;

    public MovieDto(Movies movies){
        if (movies.getMovieId() != null)
            this.movie_id = movies.getMovieId();
//        if(movies.getBody() != null)
//            this.body = notes.getBody();
    }
}
