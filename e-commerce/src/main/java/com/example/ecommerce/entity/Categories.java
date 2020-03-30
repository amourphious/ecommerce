package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Categories {
   @Id
   @Column(name = "category_id",nullable = true)
   @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
   @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    private Long categoryId;

   @Column(name = "category_name")
   private String categoryName;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "parentCategory",fetch = FetchType.LAZY)
    @JsonManagedReference(value = "parentChildRelation")
    private Set<Categories> childCategories; // always add parent category to child category


    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_category_id")
    @JsonBackReference(value = "parentChildRelation")
    private Categories parentCategory;

    @OneToMany(mappedBy = "categories",cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "productCategories")
    private Set<Product> productSet;


    // constructor getter setter

    public Categories() {
        super();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Categories getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Categories parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Categories> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set<Categories> childCategories) {
        this.childCategories = childCategories;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }


    // custom functions
    // addition of products
    public void insertProduct(Product product){
        if(productSet ==null){
         productSet=new HashSet<>();
        }
        productSet.add(product);
        product.setCategories(this);
        this.setProductSet(productSet);

    }

}
