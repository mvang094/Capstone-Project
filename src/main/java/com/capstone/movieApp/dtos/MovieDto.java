package com.capstone.movieApp.dtos;

import java.io.Serializable;
import java.util.*;

import com.capstone.movieApp.dtos.Casting_ListDto;
import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.UserDto;
import com.capstone.movieApp.entities.Casting_List;
import com.capstone.movieApp.entities.Movies;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto implements Serializable {

    private Long movie_id;
    private Long year;
    private Set<UserDto> userDto = new HashSet<>();
    private Set<UserDto> interestedDto = new HashSet<>();
    private Set<Casting_ListDto> castDto = new HashSet<>();
    private Set<Watched_ListDto> watchedDto;
    private String title,genre, maturity, keywordone,
            keywordtwo, image, trailer, summary;

    public MovieDto(Movies movies){
        if (movies.getMovieId() != null)
            this.movie_id = movies.getMovieId();
        if(movies.getYear() != null)
            this.year = movies.getYear();
        if(movies.getGenre() != null)
            this.genre = movies.getGenre();
        if(movies.getImage() != null)
            this.image = movies.getImage();
        if(movies.getKeywordone() != null)
            this.keywordone = movies.getKeywordone();
        if(movies.getKeywordtwo() != null)
            this.keywordtwo = movies.getKeywordtwo();
        if(movies.getTitle() != null)
            this.title = movies.getTitle();
        if(movies.getMaturity() != null)
            this.maturity = movies.getMaturity();
        if(movies.getTrailer() != null)
            this.trailer = movies.getTrailer();
        if(movies.getSummary() != null)
            this.summary = movies.getSummary();
    }
}
