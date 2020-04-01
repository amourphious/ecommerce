package com.example.ecommerce.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUser implements UserDetails {

    private String username;
    private String password;
    List<GrantedAuthority> grantAuthorities;

    public AppUser(String username, String password, List<GrantedAuthority> grantAuthorities) {
        this.username = username;
        this.password = password;
        this.grantAuthorities = grantAuthorities;
    }

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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}