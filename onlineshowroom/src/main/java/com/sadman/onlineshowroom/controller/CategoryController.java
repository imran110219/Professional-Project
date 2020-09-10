package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.Category;
import com.sadman.onlineshowroom.model.Product;
import com.sadman.onlineshowroom.model.Supplier;
import com.sadman.onlineshowroom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/category")
    public String getAllCategories(Model model)
    {
        List<Category> list = categoryService.getAllCategories();

        model.addAttribute("categories", list);
        return "category/category-list";
    }

    @RequestMapping(value = "/category/add")
    public String addCategoryById(Model model) throws Exception
    {
        model.addAttribute("category", new Category());
        return "category/add-category";
    }

    @RequestMapping(value = "/category/edit/{id}")
    public String editCategoryById(Model model, @PathVariable("id") Long id) throws Exception
    {
        Category entity = categoryService.getCategoryById(id);
        model.addAttribute("category", entity);
        return "category/edit-category";
    }

    @RequestMapping(value = "/category/delete/{id}")
    public String deleteCategoryById(Model model, @PathVariable("id") Long id)
            throws Exception
    {
        categoryService.deleteCategoryById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/category/createCategory", method = RequestMethod.POST)
    public String createCategory(Category category)
    {
        categoryService.createCategory(category);
        return "redirect:/category";
    }

    @RequestMapping(value = "/category/updateCategory", method = RequestMethod.POST)
    public String updateCategory(Category category)
    {
        categoryService.updateCategory(category);
        return "redirect:/category";
    }
}
