package com.example.ecommerce.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "seller")
@PrimaryKeyJoinColumn(name = "seller_id",referencedColumnName = "user_id")
public class Seller extends User {


    @NotNull(message = "gst number is required ")
    @Column(name = "gst_number",unique = true)
    private String gstNumber;

    @NotNull(message = "company name is required and should be unique")
    @Column(name = "company_name",unique = true)
    private String companyName;

    @Pattern(regexp = "((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}",message = "enter a valid phone number")
    @NotNull(message = "contact number required")
    @Column(name = "contact_number")
    private String contactNumber;



    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "seller_product_list",
            joinColumns = @JoinColumn(name = "seller_id",referencedColumnName = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "product_id"))
    private Set<Product> productList;

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
