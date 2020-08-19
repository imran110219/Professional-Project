package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Employee;
import com.sadman.onlineshowroom.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        List<Employee> result = (List<Employee>) employeeRepository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Employee>();
        }
    }

    public Employee getEmployeeById(Long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }

    public Employee createEmployee(Employee entity) {
        entity = employeeRepository.save(entity);
        return entity;
    }

    public Employee updateEmployee(Employee entity) {
        Optional<Employee> employee = employeeRepository.findById(entity.getId());

        if (employee.isPresent()) {
            Employee newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity = employeeRepository.save(newEntity);

            return newEntity;
        } else {
            entity = employeeRepository.save(entity);

            return entity;
        }
    }

    public void deleteEmployeeById(Long id) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }
}
