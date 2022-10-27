package com.capstone.movieApp.dtos;

import java.io.Serializable;

import com.capstone.movieApp.dtos.MovieDto;
import com.capstone.movieApp.dtos.UserDto;
import com.capstone.movieApp.entities.Watched_List;
import com.capstone.movieApp.entities.Watched_ListKey;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Watched_ListDto implements Serializable {

    private Watched_ListKey watch_id;
    private UserDto user;
    private MovieDto movies;
    private Boolean watched;
    private Integer rating;
    private String review;

    public Watched_ListDto (Watched_List watch){
        if (watch.getRating() != null)
            this.rating = watch.getRating();
        if(watch.getWatched() != null)
            this.watched = watch.getWatched();
        if(watch.getReview() != null)
            this.review = watch.getReview();
        if(watch.getWatch_id() != null)
            this.watch_id = watch.getWatch_id();
    }
}
