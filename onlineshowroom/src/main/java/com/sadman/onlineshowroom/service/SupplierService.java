package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Category;
import com.sadman.onlineshowroom.model.Supplier;
import com.sadman.onlineshowroom.repository.CategoryRepository;
import com.sadman.onlineshowroom.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Supplier getSupplierById(Long id) throws Exception {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if (supplier.isPresent()) {
            return supplier.get();
        } else {
            throw new Exception("No employee record exist for given id");
        }
    }

    public Supplier createSupplier(Supplier supplier) {
        supplier = supplierRepository.save(supplier);
        return supplier;
    }

    public Supplier updateSupplier(Supplier entity) {
        Optional<Supplier> supplier = supplierRepository.findById(entity.getId());

        if (supplier.isPresent()) {
            Supplier newEntity = supplier.get();
            newEntity.setSupplierName(entity.getSupplierName());
            newEntity.setPhone(entity.getPhone());
            newEntity.setAddress(entity.getAddress());

            newEntity = supplierRepository.save(newEntity);

            return newEntity;
        } else {
            entity = supplierRepository.save(entity);

            return entity;
        }
    }

    public void deleteSupplierById(Long id) throws Exception {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if (supplier.isPresent()) {
            supplierRepository.deleteById(id);
        } else {
            throw new Exception("No product record exist for given id");
        }
    }
}
