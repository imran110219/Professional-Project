package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.User;
import com.sadman.onlineshowroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value={"/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/user")
    public String getAllUsers(Model model)
    {
        List<User> list = userService.getAllUsers();
        model.addAttribute("users", list);
        return "user-list";
    }

    @RequestMapping(path = {"/user/add"})
    public String addUser(Model model) throws Exception
    {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @RequestMapping(path = {"/user/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Long id) throws Exception
    {
        User entity = userService.getUserById(id);
        model.addAttribute("user", entity);
        return "edit-user";
    }

    @RequestMapping(path = "/user/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id)
            throws Exception
    {
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    @RequestMapping(path = "/user/createUser", method = RequestMethod.POST)
    public String createUser(User user)
    {
        userService.createUser(user);
        return "redirect:/user";
    }

    @RequestMapping(path = "/user/updateUser", method = RequestMethod.POST)
    public String updateUser(User user)
    {
        userService.updateUser(user);
        return "redirect:/user";
    }
}
