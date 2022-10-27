package com.capstone.movieApp.repositories;

import com.capstone.movieApp.entities.User;
import com.capstone.movieApp.entities.Watched_List;
import com.capstone.movieApp.entities.Watched_ListKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchedRepo extends JpaRepository<Watched_List, Watched_ListKey> {

    //This is fulfilled by the List<Movies> findAllByUserEquals(User user) in MovieRepo?
    List<Watched_List> findByUser(User user);
}
