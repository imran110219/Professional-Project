package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Employee;
import com.sadman.onlineshowroom.model.Product;
import com.sadman.onlineshowroom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> result = productRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Product>();
        }
    }

    public Product getProductById(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }

    public Product createProduct(Product product) {
        product = productRepository.save(product);
        return product;
    }

    public Product updateProduct(Product entity) {
        Optional<Product> product = productRepository.findById(entity.getId());

        if (product.isPresent()) {
            Product newEntity = product.get();
            newEntity.setProductName(entity.getProductName());
            newEntity.setPrice(entity.getPrice());
            newEntity.setQuantity(entity.getQuantity());
            newEntity.setDescription(entity.getDescription());

            newEntity = productRepository.save(newEntity);

            return newEntity;
        } else {
            entity = productRepository.save(entity);

            return entity;
        }
    }

    public void deleteProductById(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new Exception("No product record exist for given id");
        }
    }
}
