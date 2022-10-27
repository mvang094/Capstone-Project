package com.capstone.movieApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

    @Bean
    //Custom bean that keep tracks of new bean called passwordEncoder
    //Hashes the password
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    };
}
