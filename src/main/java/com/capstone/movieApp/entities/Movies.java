package com.capstone.movieApp.entities;

import javax.persistence.*;

import com.capstone.movieApp.dtos.MovieDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;

    @Column
    private Long year;

    @Column
    private String title, genre, maturity,
            keywordone, keywordtwo, image, trailer;
    @Column(length = 500)
    private String summary;


//    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY) //name of the SET in Movies
//    Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "movies", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Watched_List> watched = new HashSet<>();

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

    public Long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Set<Watched_List> getWatched() {
        return watched;
    }

    public void setWatched(Set<Watched_List> watched) {
        this.watched = watched;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getKeywordone() {
        return keywordone;
    }

    public void setKeywordone(String keywordone) {
        this.keywordone = keywordone;
    }

    public String getKeywordtwo() {
        return keywordtwo;
    }

    public void setKeywordtwo(String keywordtwo) {
        this.keywordtwo = keywordtwo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    public Set<User> getInterestedUsers() {
        return interestedUsers;
    }

    public void setInterestedUsers(Set<User> interestedUsers) {
        this.interestedUsers = interestedUsers;
    }

    public Set<Casting_List> getCastlist() {
        return castlist;
    }

    public void setCastlist(Set<Casting_List> castlist) {
        this.castlist = castlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Movies movies = (Movies) o;
        return Objects.equals(title, movies.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
