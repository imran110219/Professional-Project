package com.sadman.onlineshowroom.controller;

import com.sadman.onlineshowroom.model.Supplier;
import com.sadman.onlineshowroom.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @RequestMapping(value = "/supplier")
    public String getAllSuppliers(Model model)
    {
        List<Supplier> list = supplierService.getAllSuppliers();

        model.addAttribute("suppliers", list);
        return "supplier/supplier-list";
    }

    @RequestMapping(value = "/supplier/add")
    public String addSupplierById(Model model) throws Exception
    {
        model.addAttribute("supplier", new Supplier());
        return "supplier/add-supplier";
    }

    @RequestMapping(value = "/supplier/edit/{id}")
    public String editSupplierById(Model model, @PathVariable("id") Long id) throws Exception
    {
        Supplier entity = supplierService.getSupplierById(id);
        model.addAttribute("supplier", entity);
        return "supplier/edit-supplier";
    }

    @RequestMapping(value = "/supplier/delete/{id}")
    public String deleteSupplierById(Model model, @PathVariable("id") Long id)
            throws Exception
    {
        supplierService.deleteSupplierById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/supplier/createSupplier", method = RequestMethod.POST)
    public String createSupplier(Supplier supplier)
    {
        supplierService.createSupplier(supplier);
        return "redirect:/supplier";
    }

    @RequestMapping(value = "/supplier/updateSupplier", method = RequestMethod.POST)
    public String updateSupplier(Supplier supplier)
    {
        supplierService.updateSupplier(supplier);
        return "redirect:/supplier";
    }
}
