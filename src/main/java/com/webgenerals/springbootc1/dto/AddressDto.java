package com.webgenerals.springbootc1.dto;

/**
 * AddressDto
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class AddressDto {

    private long id;
    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;
    private UserDto userDetails;

    public long getId() {
        return id;
    }

    public AddressDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public AddressDto setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getType() {
        return type;
    }

    public AddressDto setType(String type) {
        this.type = type;
        return this;
    }

    public UserDto getUserDetails() {
        return userDetails;
    }

    public AddressDto setUserDetails(UserDto userDetails) {
        this.userDetails = userDetails;
        return this;
    }

    public String getAddressId() {
        return addressId;
    }

    public AddressDto setAddressId(String addressId) {
        this.addressId = addressId;
        return this;
    }
}
