package com.sadman.inventory.interfaces;

import com.sadman.inventory.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface ProductInterface {
    
    public ObservableList<Product> PRODUCTLIST = FXCollections.observableArrayList();   
}
