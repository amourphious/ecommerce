package com.example.ecommerce.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "customer_id",referencedColumnName ="user_id" )
public class Customer extends User{

//    @Id
//    @Column(name = "customer_id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
//    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
//    private Long customerId;

    @Column(name = "contact_number",unique = true)
    private String contactNumber;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id")
//    private User user;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProductReview> productReviewList;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER  )
    private Collection<Invoice> invoiceSet;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    // getter and setter
    public Customer(){
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

//       public Long getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Long customerId) {
//        this.customerId = customerId;
//    }

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


}
