package com.sadman.onlineshowroom.repository;

import com.sadman.onlineshowroom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
