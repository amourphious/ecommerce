package com.example.ecommerce.configuration;


import com.example.ecommerce.entity.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class GrantAuthorityImpl implements GrantedAuthority {

    private List<Role> authority;

    @Override
    public String getAuthority() {
        System.out.println("................."+authority);
        for (Role auth :authority)
        {
            System.out.println(".....//////////"+auth.getAuthority());
            return auth.getAuthority();
        }
        return null;
    }

    public GrantAuthorityImpl(List<Role> authority) {
        this.authority = authority;
    }
}