package com.capstone.movieApp.dtos;

import java.io.Serializable;

import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.User;
import com.capstone.movieApp.entities.Watched_ListKey;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Watched_ListDto implements Serializable {

    Watched_ListKey watch_id;
    User user;
    Movies movies;
    boolean watched;

}
