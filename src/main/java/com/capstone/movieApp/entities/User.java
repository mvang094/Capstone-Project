package com.capstone.movieApp.entities;

import com.capstone.movieApp.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
//    @ManyToMany(cascade = {CascadeType.ALL})
//    @JoinTable( //The @joinTable is on the owning side
//            name = "Watched_List", //intermediate table
//            joinColumns = { @JoinColumn(name = "user_id")}, //foreign key
//            inverseJoinColumns = {@JoinColumn(name = "movie_id")} //foreign key
//    )
//    Set<Movies> movies = new HashSet<>(); //the set to join the two tables (i.e. movies with user, and user with movies)

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    //name is from the Watched_list class Movies movies field;
    @JsonManagedReference
    Set<Watched_List> watched;

    public User() {} //default constructor
    public User(UserDto userDto){
        if (userDto.getUsername() != null)
            this.username = userDto.getUsername();
        if (userDto.getPassword() != null)
            this.password = userDto.getPassword();
    }
    public void setId(Long id){
        this.user_id = id;
    }
    public Long getId(){
        return user_id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}
