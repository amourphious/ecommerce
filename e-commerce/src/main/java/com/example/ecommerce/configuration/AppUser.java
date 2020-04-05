package com.example.ecommerce.configuration;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class AppUser implements UserDetails {
	private final static Integer MAX_LOGIN_ATTEMPT = 3;
	
    private final String username;
    private final String password;
    private final Integer loginAttempt;
    private final Long credsInvalidAfterMillis;
    private final List<GrantedAuthority> grantAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        System.out.println("In app user");
        System.out.println((grantAuthorities));
        System.out.println("Out of app user");
       return grantAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return loginAttempt < MAX_LOGIN_ATTEMPT;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credsInvalidAfterMillis == null || System.currentTimeMillis() < credsInvalidAfterMillis;
    }

    @Override
    public boolean isEnabled() {
        return loginAttempt < MAX_LOGIN_ATTEMPT;
    }
}