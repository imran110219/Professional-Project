package com.sadman.onlineshowroom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
