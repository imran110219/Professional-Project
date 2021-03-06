package com.sadman.inventory.dao;

import com.sadman.inventory.entity.Product;
import com.sadman.inventory.entity.Sale;
import javafx.collections.ObservableList;

import java.util.List;

public interface SaleDao {

    public ObservableList<Sale> getSales();
    public Sale getSale(long id);
    public ObservableList<Sale> getSaleByProductId(long id);
    public void saveSale(Sale sale);
    public void updateSale(Sale sale);
    public void deleteSale(Sale sale);
}
