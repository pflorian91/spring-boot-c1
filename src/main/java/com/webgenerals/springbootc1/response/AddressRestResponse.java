package com.webgenerals.springbootc1.response;

import org.springframework.hateoas.RepresentationModel;

/**
 * AddressRestResponse
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class AddressRestResponse extends RepresentationModel<AddressRestResponse> {

    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;

    public String getAddressId() {
        return addressId;
    }

    public AddressRestResponse setAddressId(String addressId) {
        this.addressId = addressId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressRestResponse setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressRestResponse setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public AddressRestResponse setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressRestResponse setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getType() {
        return type;
    }

    public AddressRestResponse setType(String type) {
        this.type = type;
        return this;
    }
}
