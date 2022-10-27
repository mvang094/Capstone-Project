package com.capstone.movieApp.entities;

import javax.persistence.*;

import com.capstone.movieApp.dtos.MovieDto;
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
    private Long year;

    @OneToMany(mappedBy = "movies", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonManagedReference
    Set<Watched_List> watched;

    @Column
    private String title, genre, maturity,
            keywordone, keywordtwo, image, trailer, summary;


    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY) //name of the SET in Movies
    Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "interestedMovies", fetch = FetchType.LAZY) //name of the SET in Movies
    Set<User> interestedUsers = new HashSet<>();

    @ManyToMany(mappedBy = "movieCast", fetch = FetchType.LAZY) //name of the SET in Movies
    Set<Casting_List> castlist = new HashSet<>();

    public Movies(MovieDto movieDto){
        if(movieDto.getYear() != null)
            this.year = movieDto.getYear();
        if(movieDto.getGenre() != null)
            this.genre = movieDto.getGenre();
        if(movieDto.getImage() != null)
            this.image = movieDto.getImage();
        if(movieDto.getKeywordone() != null)
            this.keywordone = movieDto.getKeywordone();
        if(movieDto.getKeywordtwo() != null)
            this.keywordtwo = movieDto.getKeywordtwo();
        if(movieDto.getTitle() != null)
            this.title = movieDto.getTitle();
        if(movieDto.getMaturity() != null)
            this.maturity = movieDto.getMaturity();
        if(movieDto.getTrailer() != null)
            this.trailer = movieDto.getTrailer();
        if(movieDto.getSummary() != null)
            this.summary = movieDto.getSummary();
    }
    public void setMovieId(Long id){
        this.movie_id = id;
    }
    public Long getMovieId(){
        return movie_id;
    }

}
