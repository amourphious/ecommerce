package com.example.ecommerce.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    @Column(name = "invoice_id")
    private Long invoiceId;


    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Embedded
    private InvoiceAddress invoiceAddress;

    @OneToMany(mappedBy = "invoice",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<OrderProduct> orderProductSet;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Invoice(){
        super();
    }

    // getter and setter


    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public InvoiceAddress getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(InvoiceAddress invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderProduct> getOrderProductSet() {
        return orderProductSet;
    }

    public void setOrderProductSet(Set<OrderProduct> orderProductSet) {
        this.orderProductSet = orderProductSet;
    }

    // custom function to insert order product in the invoice
    public void insertOrderProduct(OrderProduct orderProduct){
        if(orderProductSet==null){
            orderProductSet=new HashSet<>();
        }
        orderProductSet.add(orderProduct);
        orderProduct.setInvoice(this);
        this.setOrderProductSet(orderProductSet);
    }

}
