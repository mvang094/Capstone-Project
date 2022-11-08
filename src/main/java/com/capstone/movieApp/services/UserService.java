package com.capstone.movieApp.services;


import com.capstone.movieApp.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    @Transactional
    //Postmethod
    List<String> addUser(UserDto userDto);

    //post method to send the body securely to the backend and retrieve the user if exists
    List<String> userLogin(UserDto userDto);

    UserDto getUser(Long userId);
}
