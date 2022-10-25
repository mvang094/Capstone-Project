package com.capstone.movieApp.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;

    @Column
    private Long movie_year;

    @OneToMany(mappedBy = "movies", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference
    Set<Watched_List> watched;

    @Column
    private String movie_title, movie_genre, movie_maturity,
            movie_keywordone, movie_keywordtwo, movie_image, movie_trailer;
//    @ManyToMany(mappedBy = "movies") //name of the SET in Movies
//    Set<User> users = new HashSet<>();

    public void setMovieId(Long id){
        this.movie_id = id;
    }
    public Long getMovieId(){
        return movie_id;
    }
}
