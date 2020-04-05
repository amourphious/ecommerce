package com.example.ecommerce.configuration;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {
    @Autowired
    UserRepository userRepository;

    AppUser loadUserByUsername(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user != null) {
        	List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
            for (Role role : user.getRolesList()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            }
            return new AppUser(user.getUsername(),
            		user.getPassword(),
            		user.getLoginAttempt(),
            		user.getExpiryDate() != null ? user.getExpiryDate().getTime() : null,
            		grantedAuthorities);
            
        }
        throw new Exception("User Not Found!!");
    }

	public void setLoginAttempt(String username, int loginAttempt) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			user.setLoginAttempt(loginAttempt);
			userRepository.save(user);
		}
	}
    
	@Nullable
	public Integer getLoginAttempt(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return user.getLoginAttempt();
		}
		return null;
	}
}
