package com.example.ecommerce.configuration;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    UserRepository userRepository;

    AppUser loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        for (Role role : user.getRolesList()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        System.out.println(user);
        if (username != null) {
            return new AppUser(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new RuntimeException();
        }

    }
}
