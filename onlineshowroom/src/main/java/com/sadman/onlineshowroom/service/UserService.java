package com.sadman.onlineshowroom.service;

import com.sadman.onlineshowroom.model.Role;
import com.sadman.onlineshowroom.model.User;
import com.sadman.onlineshowroom.repository.RoleRepository;
import com.sadman.onlineshowroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        List<User> result = (List<User>) userRepository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<User>();
        }
    }

    public User getUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("No user record exist for given id");
        }
    }

    public User createUser(User entity) {
        entity = saveUser(entity);
        return entity;
    }

    public User updateUser(User entity) {
        Optional<User> user = userRepository.findById(entity.getId());

        if (user.isPresent()) {
            User newEntity = user.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
            newEntity.setUserName(entity.getUserName());
            newEntity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
            newEntity.setPhone(entity.getPhone());

            newEntity = userRepository.save(newEntity);

            return newEntity;
        } else {
            entity = saveUser(entity);

            return entity;
        }
    }

    public void deleteUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("No user record exist for given id");
        }
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
