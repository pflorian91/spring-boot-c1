package com.webgenerals.springbootc1.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * AddressEntity
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Entity(name = "addresses")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = -196176786702060598L;

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 30, nullable = false)
    private String addressId;

    @Column(length = 15, nullable = false)
    private String city;

    @Column(length = 15, nullable = false)
    private String country;

    @Column(length = 100, nullable = false)
    private String streetName;

    @Column(length = 7, nullable = false)
    private String postalCode;

    @Column(length = 10, nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity userDetails;

    public long getId() {
        return id;
    }

    public AddressEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getAddressId() {
        return addressId;
    }

    public AddressEntity setAddressId(String addressId) {
        this.addressId = addressId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public AddressEntity setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressEntity setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getType() {
        return type;
    }

    public AddressEntity setType(String type) {
        this.type = type;
        return this;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public AddressEntity setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
        return this;
    }
}
