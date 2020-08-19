package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.Employee;
import com.sadman.onlineshowroom.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

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
        return "employee-list";
    }

    @RequestMapping(path = {"/add"})
    public String addEmployeeById(Model model) throws Exception
    {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @RequestMapping(path = {"/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Long id) throws Exception
    {
        Employee entity = service.getEmployeeById(id);
        model.addAttribute("employee", entity);
        return "edit-employee";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id)
            throws Exception
    {
        service.deleteEmployeeById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
    public String createEmployee(Employee employee)
    {
        service.createEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping(path = "/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(Employee employee)
    {
        service.updateEmployee(employee);
        return "redirect:/";
    }
}
