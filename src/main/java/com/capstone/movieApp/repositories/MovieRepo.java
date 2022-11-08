package com.capstone.movieApp.repositories;

import com.capstone.movieApp.entities.Casting_List;
import com.capstone.movieApp.entities.Movies;
import com.capstone.movieApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movies, Long> {
    /*  Finds movies based on their ids. The purpose of this is so that
    if a user clicks on a movie for more details, it will send a GET request
    with the movieId to find the movie in the database (if it exists)
     */
    @Override
    Optional<Movies> findById(Long movieId);
    Optional<Movies> findByTitle(String movieName);

//    Same thing here, except with the name, i.e. using the search bar, find the ratings
//    Optional<Movies> findBylala_title(String movieName); //lala is the propertty name. It must match a field in the Movies table

//    Returns all the instances of the Movies i.e. for the 'All' html page

//    List<Movies> findAll();

//    //Finds the list of movies by user
//    List<Movies> findAllByusersEquals(User user);

    //Finds the list of movies with the cast member in it
    List<Movies> findAllBycastlist(Casting_List cast);

    //Finds the list of movies by genre
    List<Movies> findAllBygenre(String genre);

    //Arraylist allows for duplicats so use Set or Hashset
}
