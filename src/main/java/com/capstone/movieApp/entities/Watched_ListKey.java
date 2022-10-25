package com.capstone.movieApp.entities;

import javax.persistence.*;
import java.io.Serializable;

//creates a key that becomes the primary key for the intermediate table Watched_List
@Embeddable
public class Watched_ListKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "movie_id")
    Long movieId;
}
