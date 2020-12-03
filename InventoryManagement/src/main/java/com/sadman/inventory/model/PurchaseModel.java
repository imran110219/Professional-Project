package com.sadman.inventory.model;

import com.sadman.inventory.util.HibernateUtil;
import com.sadman.inventory.dao.PurchaseDao;
import com.sadman.inventory.entity.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.util.List;

public class PurchaseModel implements PurchaseDao {

    private static Session session;
    
    @Override
    public ObservableList<Purchase> getPurchases() {
        
        ObservableList<Purchase> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Purchase> products = session.createQuery("from Purchase").list();
        session.getTransaction().commit();
        session.close();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public Purchase getPurchase(long id) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Purchase purchase = session.get(Purchase.class, id);
        session.getTransaction().commit();
        session.close();
        return purchase;
    }

    @Override
    public void savePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(purchase);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        p.setProduct(purchase.getProduct());
        p.setSupplier(purchase.getSupplier());
        p.setQuantity(purchase.getQuantity());
        p.setDate(purchase.getDate());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletePurchase(Purchase purchase) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Purchase p = session.get(Purchase.class, purchase.getId());
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }
    
}
