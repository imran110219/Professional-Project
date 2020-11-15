package com.sadman.inventory.dao;

import com.sadman.inventory.entity.Invoice;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public interface InvoiceDao {
 
    public ObservableList<Invoice> getInvoices();
    public List<Invoice> getInvoicesByDate();
    public Invoice getInvoice(String id);
    public void saveInvoice(Invoice invoice);
    public void deleteCategory(Invoice invoice);
}
