package com.sadman.inventory.model;

import com.sadman.inventory.util.HibernateUtil;
import com.sadman.inventory.dao.EmployeeDao;
import com.sadman.inventory.entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class EmployeeModel implements EmployeeDao {

    private static Session session;

    @Override
    public ObservableList<Employee> getEmployees() {

        ObservableList<Employee> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Employee> employees = session.createQuery("from Employee").list();
        session.getTransaction().commit();
        session.close();
        employees.stream().forEach(list::add);

        return list;
    }

    @Override
    public Employee getEmployee(long id) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee employee = session.get(Employee.class, id);
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    @Override
    public Employee getEmployeeByUsername(String username) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return employee;
    }
    
    @Override
    public String getEmployeeType(String username){
    
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return employee.getType();
    }

    @Override
    public void saveEmployee(Employee employee) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateEmployee(Employee employee) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        e.setFirstName(employee.getFirstName());
        e.setLastName(employee.getLastName());
        e.setUserName(employee.getUserName());
        e.setPassword(employee.getPassword());
        e.setPhone(employee.getPhone());
        e.setAddress(employee.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee e = session.get(Employee.class, employee.getId());
        session.delete(e);
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public boolean checkUser(String username) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return employee != null;
    }
    
    @Override
    public boolean checkPassword(String username, String password) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where userName = :username");
        query.setParameter("username", username);
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return employee.getPassword().equals(password);
    }

    @Override
    public String getAdminName() {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Employee where type = :type");
        query.setParameter("type", "admin");
        Employee employee = (Employee) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return employee.getFirstName() + " " + employee.getLastName();
    }
}
