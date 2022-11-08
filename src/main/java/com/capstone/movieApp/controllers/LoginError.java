package com.capstone.movieApp.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginError implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    String error(HttpServletRequest request){
        return "<h4>Wrong Username or Password</h4>";
    }
}
