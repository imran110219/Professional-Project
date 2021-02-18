package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Role;
import com.sadman.onlineshowroom.model.Supplier;
import com.sadman.onlineshowroom.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        List<Role> result = roleRepository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Role>();
        }
    }

    public Set<Role> getAllRoleSet() {
        List<Role> result = getAllRoles();
        Set<Role> roleSet = new HashSet<Role>(result);

        return roleSet;
    }
}
