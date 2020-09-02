package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Supplier;
import com.sadman.onlineshowroom.repository.CategoryRepository;
import com.sadman.onlineshowroom.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        List<Supplier> result = supplierRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Supplier>();
        }
    }
}
