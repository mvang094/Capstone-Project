package com.capstone.movieApp.repositories;

import com.capstone.movieApp.entities.Casting_List;
import com.capstone.movieApp.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CastingRepo extends JpaRepository<Casting_List, Long> {

    @Override
    Optional<Casting_List> findById(Long cast_id);
    Optional<Casting_List> findByCastName(String cast_name);
}
