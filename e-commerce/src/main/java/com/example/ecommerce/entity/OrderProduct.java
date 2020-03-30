package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "order_product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orderProductId")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    @Column(name = "order_product_id")
    private Long orderProductId;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonBackReference(value = "invoiceOrderProduct")
    Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    @JsonBackReference(value = "productVariationOrder")
    ProductVariation productVariation;

    @OneToOne(mappedBy = "orderProduct",cascade = CascadeType.ALL)
    OrderStatus orderStatus;

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    // custom method to

    public void setStataus(OrderStatus orderStatus){
        this.setOrderStatus(orderStatus);
        orderStatus.setOrderProduct(this);
    }
}
