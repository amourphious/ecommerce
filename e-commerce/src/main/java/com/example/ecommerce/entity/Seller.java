package com.example.ecommerce.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "seller")
@PrimaryKeyJoinColumn(name = "seller_id",referencedColumnName = "user_id")
public class Seller extends User{

//    @Id
//    @Column(name = "seller_id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
//    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
//    private Long sellerId;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id")
//    User user;


    @Column(name = "gst_number",unique = true)
    private String gstNumber;

    @Column(name = "company_name",unique = true)
    private String comapnyName;

    @Column(name = "contact_number",unique = true)
    private String contactNumber;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_product_list",
            joinColumns = @JoinColumn(name = "seller_id",referencedColumnName = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "product_id"))
    private Set<Product> productList;


//    public Long getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(Long sellerId) {
//        this.sellerId = sellerId;
//    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getComapnyName() {
        return comapnyName;
    }

    public void setComapnyName(String comapnyName) {
        this.comapnyName = comapnyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }
}
