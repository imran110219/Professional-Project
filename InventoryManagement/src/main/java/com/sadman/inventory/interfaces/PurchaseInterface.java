package com.sadman.inventory.interfaces;

import com.sadman.inventory.entity.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface PurchaseInterface {
    
    public ObservableList<Purchase> PURCHASELIST = FXCollections.observableArrayList();
}
