package com.sadman.inventory.dao;

import com.sadman.inventory.entity.Category;
import javafx.collections.ObservableList;

public interface CategoryDao {
    
    public ObservableList<Category> getCategories();
    public Category getCategory(long id);
    public void saveCategory(Category category);
    public void updateCategory(Category category);
    public void deleteCategory(Category category);
    public ObservableList<String> getTypes();
    public boolean checkCategory(String type);
}
