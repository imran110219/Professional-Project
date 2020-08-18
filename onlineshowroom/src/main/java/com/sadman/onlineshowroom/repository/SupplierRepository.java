package com.sadman.onlineshowroom.repository;

import com.sadman.onlineshowroom.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
