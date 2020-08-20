package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.Employee;
import com.sadman.onlineshowroom.model.Product;
import com.sadman.onlineshowroom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/product/")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping
    public String getAllProducts(Model model)
    {
        List<Product> list = productService.getAllProducts();

        model.addAttribute("products", list);
        return "product-list";
    }

    @RequestMapping(path = {"/add"})
    public String addProductById(Model model) throws Exception
    {
        model.addAttribute("product", new Product());
        return "add-employee";
    }

    @RequestMapping(path = {"/edit/{id}"})
    public String editProductById(Model model, @PathVariable("id") Long id) throws Exception
    {
        Product entity = productService.getProductById(id);
        model.addAttribute("product", entity);
        return "edit-employee";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteProductById(Model model, @PathVariable("id") Long id)
            throws Exception
    {
        productService.deleteProductById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createProduct", method = RequestMethod.POST)
    public String createProduct(Product product)
    {
        productService.createProduct(product);
        return "redirect:/";
    }

    @RequestMapping(path = "/updateProduct", method = RequestMethod.POST)
    public String updateProduct(Product product)
    {
        productService.updateProduct(product);
        return "redirect:/";
    }
}
