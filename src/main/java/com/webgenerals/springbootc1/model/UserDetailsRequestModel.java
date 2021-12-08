package com.webgenerals.springbootc1.model;

import java.util.List;

/**
 * UserDetailsRequestModel
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class UserDetailsRequestModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressRequestModel> addresses;

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsRequestModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsRequestModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsRequestModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDetailsRequestModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<AddressRequestModel> getAddresses() {
        return addresses;
    }

    public UserDetailsRequestModel setAddresses(List<AddressRequestModel> addresses) {
        this.addresses = addresses;
        return this;
    }
}
