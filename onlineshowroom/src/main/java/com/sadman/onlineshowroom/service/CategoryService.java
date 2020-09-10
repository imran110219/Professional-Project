package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Category;
import com.sadman.onlineshowroom.model.Product;
import com.sadman.onlineshowroom.model.Supplier;
import com.sadman.onlineshowroom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Category getCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            return category.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }

    public Category createCategory(Category category) {
        category = categoryRepository.save(category);
        return category;
    }

    public Category updateCategory(Category entity) {
        Optional<Category> category = categoryRepository.findById(entity.getId());

        if (category.isPresent()) {
            Category newEntity = category.get();
            newEntity.setCategoryName(entity.getCategoryName());
            newEntity.setCategoryDescription(entity.getCategoryDescription());

            newEntity = categoryRepository.save(newEntity);

            return newEntity;
        } else {
            entity = categoryRepository.save(entity);

            return entity;
        }
    }

    public void deleteCategoryById(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new Exception("No product record exist for given id");
        }
    }
}
