package com.example.ecommerce.entity;

import com.example.ecommerce.enums.FromStatus;
import com.example.ecommerce.enums.ToStatus;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @Column(name ="order_product_status_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order_product_id")
    OrderProduct orderProduct;


    @Column(name = "transition_comment")
    private String transitionComment;

    public OrderStatus(){ }

    @Enumerated(EnumType.STRING)
    private FromStatus fromStatus;

    @Enumerated(EnumType.STRING)
    private ToStatus toStatus;

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public String getTransitionComment() {
        return transitionComment;
    }

    public void setTransitionComment(String transitionComment) {
        this.transitionComment = transitionComment;
    }

    public FromStatus getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(FromStatus fromStatus) {
        this.fromStatus = fromStatus;
    }

    public ToStatus getToStatus() {
        return toStatus;
    }

    public void setToStatus(ToStatus toStatus) {
        this.toStatus = toStatus;
    }
}
