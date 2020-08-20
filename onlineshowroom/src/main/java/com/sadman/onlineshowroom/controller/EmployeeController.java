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

@Controller
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/user")
    public String getAllEmployees(Model model)
    {
        List<Employee> list = employeeService.getAllEmployees();

        model.addAttribute("employees", list);
        return "employee-list";
    }

    @RequestMapping(path = {"/user/add"})
    public String addEmployeeById(Model model) throws Exception
    {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @RequestMapping(path = {"/user/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Long id) throws Exception
    {
        Employee entity = employeeService.getEmployeeById(id);
        model.addAttribute("employee", entity);
        return "edit-employee";
    }

    @RequestMapping(path = "/user/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id)
            throws Exception
    {
        employeeService.deleteEmployeeById(id);
        return "redirect:/user";
    }

    @RequestMapping(path = "/user/createEmployee", method = RequestMethod.POST)
    public String createEmployee(Employee employee)
    {
        employeeService.createEmployee(employee);
        return "redirect:/user";
    }

    @RequestMapping(path = "/user/updateEmployee", method = RequestMethod.POST)
    public String updateEmployee(Employee employee)
    {
        employeeService.updateEmployee(employee);
        return "redirect:/user";
    }
}
