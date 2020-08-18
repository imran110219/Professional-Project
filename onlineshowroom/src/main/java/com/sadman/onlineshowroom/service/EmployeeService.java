package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Employee;
import com.sadman.onlineshowroom.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees()
    {
        List<Employee> result = (List<Employee>) employeeRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Employee>();
        }
    }
}
