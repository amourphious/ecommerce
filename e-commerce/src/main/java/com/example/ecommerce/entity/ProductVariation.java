package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Entity
@Table(name = "product_variation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "productVariationId")
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator", sequenceName = "identity_table", allocationSize = 1)
    @Column(name = "product_variation_id")
    private Long productVariationId;

    private Integer quantity;

    private Double Price;

    @Column(name = "img_name")
    private String imageName;

    @Type(type = "json")
    @Column(name = "product_metadata", columnDefinition = "json")
    private String metadata;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "productVariation")
    Product product;

    @OneToMany(mappedBy = "productVariation",fetch = FetchType.LAZY)
    @JsonManagedReference(value = "productVariationOrder")
    Collection<OrderProduct> orderProduct;

    @ManyToMany(mappedBy = "productVariationSet")
    @JsonBackReference(value = "productVariationCart")
    private Set<Cart> cartSet;


    // getter and setter
    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Collection<OrderProduct> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Collection<OrderProduct> orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Set<Cart> getCartSet() {
        return cartSet;
    }

    public void setCartSet(Set<Cart> cartSet) {
        this.cartSet = cartSet;
    }
}
