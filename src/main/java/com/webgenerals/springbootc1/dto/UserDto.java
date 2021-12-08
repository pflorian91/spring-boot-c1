package com.webgenerals.springbootc1.dto;

import java.io.Serializable;
import java.util.List;

/**
 * UserDto
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = -2787179959211900945L;

    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private boolean emailVerificationStatus = false;
    private List<AddressDto> addresses;

    public long getId() {
        return id;
    }

    public UserDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public UserDto setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public UserDto setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
        return this;
    }

    public boolean isEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public UserDto setEmailVerificationStatus(boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
        return this;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public UserDto setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
        return this;
    }
}
