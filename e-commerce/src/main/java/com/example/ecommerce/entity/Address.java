package com.example.ecommerce.entity;

import javax.persistence.*;

@Embeddable
@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "identity_generator")
    @SequenceGenerator(name = "identity_generator",sequenceName = "identity_table",allocationSize = 1)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name="street_address")
    private String streetAddress;


    private String city;


    private String state;

    private String country;

    @Column(name="zip_code")
    private Integer zipCode;

    private String label;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    public Address(){
        super();

    }

    public Address(String streetAddress, String city, String state, String country, Integer zipCode, String label) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.label = label;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode=" + zipCode +
                ", label='" + label + '\'' +
                ", user=" + user +
                '}';
    }
}
