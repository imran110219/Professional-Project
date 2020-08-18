package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.Employee;
import com.sadman.onlineshowroom.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeControler {
    @Autowired
    EmployeeService service;

    @RequestMapping
    public String getAllEmployees(Model model)
    {
        List<Employee> list = service.getAllEmployees();

        model.addAttribute("employees", list);
        return "list-employees";
    }
}
