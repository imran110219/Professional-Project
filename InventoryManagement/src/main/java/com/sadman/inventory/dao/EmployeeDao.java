package com.sadman.inventory.dao;

import com.sadman.inventory.entity.Employee;
import javafx.collections.ObservableList;

public interface EmployeeDao {
    
    public ObservableList<Employee> getEmployees();
    public Employee getEmployee(long id);
    public Employee getEmployeeByUsername(String username);
    public String getEmployeeType(String username);
    public void saveEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
    public boolean checkPassword(String username, String password);
    public boolean checkUser(String username);
}
