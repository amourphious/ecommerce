package com.example.ecommerce.entity;

import javax.persistence.*;
@Embeddable
public class InvoiceAddress {

    @Column(name="street_address")
    String streetAddress;

    String city;


    String state;


    String country;

    @Column(name="zip_code")
    Integer zipCode;

    String label;

    public InvoiceAddress(){
        super();
    }

    public InvoiceAddress(Address currentAddress){
        streetAddress=currentAddress.getStreetAddress();
        city=currentAddress.getCity();
        state=currentAddress.getState();
        country=currentAddress.getCountry();
        zipCode=currentAddress.getZipCode();
        label=currentAddress.getLabel();
    }

}
