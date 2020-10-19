package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.Category;
import com.sadman.onlineshowroom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
class HomeController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/")
    public String index(Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        return "index";
    }
}
