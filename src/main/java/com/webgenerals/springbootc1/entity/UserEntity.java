package com.webgenerals.springbootc1.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * UserEntity
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Entity(name = "users")
public class UserEntity implements Serializable {

    public static final long serialVersionUID = 523899158791436817L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean emailVerificationStatus = false;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List<AddressEntity> addresses;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public UserEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public UserEntity setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public UserEntity setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
        return this;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public UserEntity setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
        return this;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public UserEntity setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
        return this;
    }
}
