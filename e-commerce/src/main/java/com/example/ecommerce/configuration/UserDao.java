package com.example.ecommerce.configuration;


import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class UserDao {

    @Autowired
    UserRepository userRepository;

    AppUser loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        System.out.println(user);
        if (username != null) {
            return new AppUser(user.getFirstName(), user.getPassword(), Arrays.asList(new GrantAuthorityImpl(user.getRolesList())));
        } else {
            throw new RuntimeException();
        }

    }
}
