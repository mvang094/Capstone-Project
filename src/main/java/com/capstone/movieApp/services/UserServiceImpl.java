package com.capstone.movieApp.services;

import com.capstone.movieApp.dtos.UserDto;
import com.capstone.movieApp.entities.User;
import com.capstone.movieApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepo.saveAndFlush(user); //This is where the user gets saved into the database(persisted)
//        response.add("http://localhost:8085/login.html");
        response.add("login.html");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepo.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
//                response.add("http://localhost:8085/home.html");
                response.add("home.html");
                response.add(String.valueOf(userOptional.get().getId()));
                response.add(String.valueOf(userOptional.get().getUsername()));
            } else
                response.add("Username or password incorrect");
        } else
            response.add("Username or password incorrect");

        return response;
    }

    @Override
    public UserDto getUser(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        UserDto userDto = new UserDto(userOptional.get());

        return userDto;
    }
}
