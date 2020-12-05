package com.sadman.inventory.model;

import com.sadman.inventory.entity.Product;
import com.sadman.inventory.util.HibernateUtil;
import com.sadman.inventory.dao.SaleDao;
import com.sadman.inventory.entity.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SalesModel implements SaleDao {

    private static Session session;

    @Override
    public ObservableList<Sale> getSales() {

        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Sale> products = session.createQuery("from Sale").list();
        session.getTransaction().commit();
        session.close();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public ObservableList<Sale> getSaleByProductId(long id) {

        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Sale> products = (List<Sale>) session.createCriteria(Sale.class)
                .add(Restrictions.eq("product.id", id)).list();

        session.getTransaction().commit();
        session.close();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public Sale getSale(long id) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Sale sale = session.get(Sale.class, id);
        session.getTransaction().commit();
        session.close();
        return sale;
    }

    @Override
    public void saveSale(Sale sale) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(sale);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        s.setProduct(sale.getProduct());
        s.setQuantity(sale.getQuantity());
        s.setPrice(sale.getPrice());
        s.setTotal(sale.getTotal());
        s.setDate(sale.getDate());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        session.delete(s);
        session.getTransaction().commit();
        session.close();
    }
}
