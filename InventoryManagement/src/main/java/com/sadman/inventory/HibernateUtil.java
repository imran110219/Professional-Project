package com.sadman.inventory;

import com.sadman.inventory.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static boolean setSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/inventory?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "12345678");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
//                settings.put(Environment.STATEMENT_BATCH_SIZE, "5");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Invoice.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(Purchase.class);
                configuration.addAnnotatedClass(Sale.class);
                configuration.addAnnotatedClass(Supplier.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
//                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
