package com.sadman.inventory.model;

import com.sadman.inventory.HibernateUtil;
import com.sadman.inventory.dao.InvoiceDao;
import com.sadman.inventory.entity.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class InvoiceModel implements InvoiceDao {

    private static Session session;

    @Override
    public ObservableList<Invoice> getInvoices() {

        ObservableList<Invoice> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Invoice> products = session.createQuery("from Invoice").list();
        session.getTransaction().commit();
        session.close();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public List<Invoice> getInvoicesByDate() {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Invoice> invoices = session.createQuery("from Invoice where date < current_date").list();
        session.getTransaction().commit();
        session.close();

        return invoices;
    }

    @Override
    public Invoice getInvoice(String id) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Invoice invoice = session.get(Invoice.class, id);
        session.getTransaction().commit();
        session.close();

        return invoice;
    }

    @Override
    public void saveInvoice(Invoice invoice) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(invoice);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCategory(Invoice invoice) {

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Invoice i = session.get(Invoice.class, invoice.getId());
        session.delete(i);
        session.getTransaction().commit();
        session.close();
    }

}
