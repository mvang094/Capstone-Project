package com.capstone.movieApp.repositories;

import com.capstone.movieApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//The Layer that acts with the Database directly. Performs all the CRUD operations
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    //If the user exists when logging in, then find the user and return true
    Optional<User> findByUsername(String username);
    //derived query methods must have matching fields
    List<User> findByadmin(Boolean admin);
}
