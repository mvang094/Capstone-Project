package com.capstone.movieApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
//@Controller
public class  MovieAppApplication {

//	@RequestMapping("/")
//	@ResponseBody
//	String home() {
//		return ("./login.html");
//	}

	public static void main(String[] args) {
		SpringApplication.run(MovieAppApplication.class, args);
	}

}
