package com.example.ecommerce.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    private Long roleId;


    private String authority;

    @ManyToMany(mappedBy = "rolesList")
    private List<User> userList;

    public Role(){
        super();
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
