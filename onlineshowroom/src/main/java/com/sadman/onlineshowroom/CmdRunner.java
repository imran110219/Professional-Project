//package com.sadman.onlineshowroom;
//
//import com.sadman.onlineshowroom.model.Role;
//import com.sadman.onlineshowroom.model.User;
//import com.sadman.onlineshowroom.repository.RoleRepository;
//import com.sadman.onlineshowroom.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class CmdRunner implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(CmdRunner.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    private List<Role> rolelist = roleRepository.findAll();
//    private Set<Role> roleset = new HashSet<Role>(rolelist);
//
//    @Override
//    public void run(String... args) throws Exception {
//        userRepository.deleteAll();
//        userRepository.save(new User("john", "cena", "admin", "admin@gmail.com", "12345678", "0099887766", true, roleset));
//        userRepository.save(new User("john", "doe", "user", "user@gmail.com", "12345678", "0099887766", true, roleset));
//
//        userRepository.findAll().forEach((user) -> {
//            logger.info("{}", user);
//        });
//    }
//}