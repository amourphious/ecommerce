package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "customer_id",referencedColumnName ="user_id" )
public class Customer extends User{

    @Column(name = "contact_number")
    private String contactNumber;

    @JsonIgnore
    @Column(name = "activation_token")
    private String activationToken;

    @JsonIgnore
    @Column(name = "token_expiry_date")
    private Date expiryDate;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "customerReview")
    private List<ProductReview> productReviewList;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "customerInvoice")
    private Collection<Invoice> invoiceSet;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    // getter and setter
    public Customer(){
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    // product review getter and setter
    public List<ProductReview> getProductReviewList() {
        return productReviewList;
    }

    public void setProductReviewList(List<ProductReview> productReviewList) {
        this.productReviewList = productReviewList;
    }

    // customer order getter and setter

    public Collection<Invoice> getInvoiceSet() {
        return invoiceSet;
    }

    public void setInvoiceSet(Collection<Invoice> invoiceSet) {
        this.invoiceSet = invoiceSet;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // custom add and remove method
    // inserting of review by the customer

    public void insertProductReview(ProductReview productReview){
        if(productReviewList==null){
            productReviewList=new ArrayList<>();
        }
        productReviewList.add(productReview);
        productReview.setCustomer(this);
        this.setProductReviewList(productReviewList);
    }

    // creating the orders of the customer;
    public void createInvoice(Invoice invoice){
        if(invoiceSet==null){
            invoiceSet=new HashSet<>();
        }
        invoiceSet.add(invoice);
        invoice.setCustomer(this);
        this.setInvoiceSet(invoiceSet);
    }

    public void createCart(Cart cart){
        this.setCart(cart);
        cart.setCustomer(this);
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int expiryTimeInMinutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        this.expiryDate = new Date(cal.getTime().getTime());;
    }

}
