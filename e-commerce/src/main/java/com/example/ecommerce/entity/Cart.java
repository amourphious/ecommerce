package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    private Long cartId;

    private Integer quantity;

    @Column(name = "is_wishlist_item")
    private boolean isWishlistItem;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    Customer customer;

    @ManyToMany
    @JoinTable(name = "product_cart_id",joinColumns =@JoinColumn(name ="cart_id",referencedColumnName ="cart_id"),inverseJoinColumns =@JoinColumn(name ="variation_id",referencedColumnName ="product_variation_id" ))
    private Set<ProductVariation> productVariationSet;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isWishlistItem() {
        return isWishlistItem;
    }

    public void setWishlistItem(boolean wishlistItem) {
        isWishlistItem = wishlistItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ProductVariation> getProductVariationSet() {
        return productVariationSet;
    }

    public void setProductVariationSet(Set<ProductVariation> productVariationSet) {
        this.productVariationSet = productVariationSet;
    }
}
