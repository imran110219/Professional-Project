package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Category;
import com.sadman.onlineshowroom.model.Product;
import com.sadman.onlineshowroom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<Category> result = categoryRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Category>();
        }
    }
}
