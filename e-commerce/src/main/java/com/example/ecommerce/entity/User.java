package com.example.ecommerce.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    private Long userId;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role_list",
            joinColumns = @JoinColumn(name = "u_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "r_id",referencedColumnName = "role_id"))
    private List<Role> rolesList=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Address> addressSet;

//    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
//    Customer customer;
//
//    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
//    Seller seller;

    public User(){

    }

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Seller getSeller() {
//        return seller;
//    }
//
//    public void setSeller(Seller seller) {
//        this.seller = seller;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

//    public void createCustomer(Customer cobj){
//       this.setCustomer(cobj);
//       cobj.setUser(this);
//    }
//
//    public void createSeller(Seller sobj){
//        this.setSeller(sobj);
//        sobj.setUser(this);
//    }

    public void addAddress(Address address){
        if(addressSet==null){
            addressSet=new HashSet<>();
        }

        addressSet.add(address);
        address.setUser(this);
        this.setAddressSet(addressSet);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" +  + '\'' +
                '}';
    }
}
