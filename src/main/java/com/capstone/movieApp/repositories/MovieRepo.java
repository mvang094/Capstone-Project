package com.capstone.movieApp.repositories;

import com.capstone.movieApp.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movies, Long> {
    @Override
    Optional<Movies> findById(Long movieId);

}
