package com.example.ecommerce.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_review")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_review_id;

    private String review;

    private Double rating;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // parameterized constructor

    public ProductReview(){

    }

    public ProductReview(String review, Double rating, Product product) {
        super();
        this.review = review;
        this.rating = rating;
        this.product = product;
    }


    // getter setter

    public Long getProduct_review_id() {
        return product_review_id;
    }

    public void setProduct_review_id(Long product_review_id) {
        this.product_review_id = product_review_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    // product getter setter

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // user getter setter

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



}