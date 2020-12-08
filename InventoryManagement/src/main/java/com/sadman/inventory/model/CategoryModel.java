package com.sadman.inventory.model;

import com.sadman.inventory.entity.Employee;
import com.sadman.inventory.util.HibernateUtil;
import com.sadman.inventory.dao.CategoryDao;
import com.sadman.inventory.entity.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import java.util.List;

public class CategoryModel implements CategoryDao {

    private static Session session;

    @Override
    public ObservableList<Category> getCategories() {

        ObservableList<Category> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Category> categories = session.createQuery("from Category").list();
        session.getTransaction().commit();
        session.close();
        categories.stream().forEach(list::add);

        return list;
    }

    @Override
    public Category getCategory(long id) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category category = session.get(Category.class, id);
        session.getTransaction().commit();
        session.close();
        return category;
    }

    @Override
    public void saveCategory(Category category) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateCategory(Category category) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category c = session.get(Category.class, category.getId());
        c.setType(category.getType());
        c.setDescription(category.getDescription());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCategory(Category category) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Category c = session.get(Category.class, category.getId());
        session.delete(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public ObservableList<String> getTypes() {
        
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Category.class);
        criteria.setProjection(Projections.property("type"));
        ObservableList<String> list = FXCollections.observableArrayList(criteria.list());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public boolean checkCategory(String type) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Category where type = :type");
        query.setParameter("type", type);
        Category category = (Category) query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return category != null;
    }

}
