package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "productId")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    private String description;

    private String brand;

    @Column(name = "is_cancellable")
    private boolean isCancellable;

    @Column(name = "is_returnable")
    private boolean isReturnable;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "productCategories")
    private Categories categories;

    @ManyToMany(mappedBy = "productList",fetch = FetchType.LAZY)
    Set<Seller> sellerList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "productVariation")
    private Set<ProductVariation> productVariationSet;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "productReview")
    private List<ProductReview> productReviewList;

    // product getter or setter

    public Product(){
        super();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    // product seller getter setter

    public Set<Seller> getSellerList() {
        return sellerList;
    }

    public void setSellerList(Set<Seller> sellerList) {
        this.sellerList = sellerList;
    }

    // product variation getter setter

    public Set<ProductVariation> getProductVariationSet() {
        return productVariationSet;
    }

    public void setProductVariationSet(Set<ProductVariation> productVariationSet) {
        this.productVariationSet = productVariationSet;
    }

    // product review getter and setter


    public List<ProductReview> getProductReviewList() {
        return productReviewList;
    }

    public void setProductReviewList(List<ProductReview> productReviewList) {
        this.productReviewList = productReviewList;
    }

    // the custom insert or remove methods
    // product variation
    public void insertProductVariation(ProductVariation productVariation){
        if(productVariationSet==null){
            productVariationSet=new HashSet<>();
        }
        productVariationSet.add(productVariation);
        this.setProductVariationSet(productVariationSet);
        productVariation.setProduct(this);
    }

}
