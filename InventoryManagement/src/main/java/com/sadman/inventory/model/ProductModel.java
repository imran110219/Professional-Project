package com.sadman.inventory.model;

import com.sadman.inventory.entity.Category;
import com.sadman.inventory.entity.Invoice;
import com.sadman.inventory.entity.Supplier;
import com.sadman.inventory.util.HibernateUtil;
import com.sadman.inventory.dao.ProductDao;
import com.sadman.inventory.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProductModel implements ProductDao {

    private static Session session;

    @Override
    public ObservableList<Product> getProducts() {

        ObservableList<Product> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product").list();
        session.getTransaction().commit();
        session.close();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public Product getProduct(long id) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public Product getProductByName(String productName) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product where productName=:name");
        query.setParameter("name", productName);
        Product product = (Product) query.uniqueResult();
        session.close();

        return product;
    }

    @Override
    public void saveProduct(Product product) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateProduct(Product product) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setProductName(product.getProductName());
        p.setCategory(product.getCategory());
        p.setQuantity(product.getQuantity());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public void increaseProduct(Product product){
    
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setQuantity(product.getQuantity());
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public void decreaseProduct(Product product){
    
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setQuantity(product.getQuantity());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteProduct(Product product) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public ObservableList<String> getProductNames(){
    
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Product.class);
        criteria.setProjection(Projections.property("productName"));
        ObservableList<String> list = FXCollections.observableArrayList(criteria.list());
        session.getTransaction().commit();
        session.close();
        
        return list;
    }

    @Override
    public List<Product> getProductListByCategory(Category category){

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long id = category.getId();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("category.id",id));
        List<Product> products = crit.list();
        session.getTransaction().commit();
        session.close();

        return products;
    }

    @Override
    public List<Product> getProductListBySupplier(Supplier supplier){

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long id = supplier.getId();
        Criteria crit = session.createCriteria(Product.class);
        crit.add(Restrictions.eq("supplier.id",id));
        List<Product> products = crit.list();
        session.getTransaction().commit();
        session.close();

        return products;
    }

    @Override
    public boolean checkProduct(String productName) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product where productName=:name");
        query.setParameter("name", productName);
        Product product = (Product) query.uniqueResult();
        session.close();

        return product != null;
    }
}
