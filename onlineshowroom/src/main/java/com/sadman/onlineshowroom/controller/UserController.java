package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.User;
import com.sadman.onlineshowroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
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
        return "user/user-list";
    }

    @RequestMapping(path = {"/user/add"})
    public String addUser(Model model) throws Exception
    {
        model.addAttribute("user", new User());
        return "user/add-user";
    }

    @RequestMapping(path = {"/user/edit/{id}"})
    public String editUserById(Model model, @PathVariable("id") Long id) throws Exception
    {
        User entity = userService.getUserById(id);
        model.addAttribute("user", entity);
        return "user/edit-user";
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

    @GetMapping(value="/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
}
