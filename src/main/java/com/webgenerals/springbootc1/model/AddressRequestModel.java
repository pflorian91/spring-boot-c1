package com.webgenerals.springbootc1.model;

/**
 * AddressRequestModel
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class AddressRequestModel {

    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;

    public String getCity() {
        return city;
    }

    public AddressRequestModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressRequestModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public AddressRequestModel setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressRequestModel setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getType() {
        return type;
    }

    public AddressRequestModel setType(String type) {
        this.type = type;
        return this;
    }
}
