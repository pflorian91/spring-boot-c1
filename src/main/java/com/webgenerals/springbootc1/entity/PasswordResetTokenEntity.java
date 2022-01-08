package com.webgenerals.springbootc1.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * PasswordResetTokenEntity
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Entity(name = "password_reset_tokens")
public class PasswordResetTokenEntity implements Serializable {

    private static final long serialVersionUID = 1277496952496967259L;

    @Id
    @GeneratedValue
    private long id;

    private String token;

    @OneToOne()
    @JoinColumn(name = "users_id")
    private UserEntity userDetails;

    public long getId() {
        return id;
    }

    public PasswordResetTokenEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return token;
    }

    public PasswordResetTokenEntity setToken(String token) {
        this.token = token;
        return this;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public PasswordResetTokenEntity setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
        return this;
    }
}
