package com.sadman.inventory.dao;

import com.sadman.inventory.entity.Category;
import com.sadman.inventory.entity.Product;
import com.sadman.inventory.entity.Supplier;
import javafx.collections.ObservableList;

import java.util.List;

public interface ProductDao {
    
    public ObservableList<Product> getProducts();
    public Product getProduct(long id);
    public Product getProductByName(String productName);
    public void saveProduct(Product product);
    public void updateProduct(Product product);
    public void decreaseProduct(Product product);
    public void deleteProduct(Product product);
    public ObservableList<String> getProductNames();
    public void increaseProduct(Product product);
    public List<Product> getProductListByCategory(Category category);
    public List<Product> getProductListBySupplier(Supplier supplier);
    public boolean checkProduct(String productName);
}
