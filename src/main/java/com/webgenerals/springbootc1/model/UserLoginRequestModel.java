package com.webgenerals.springbootc1.model;

/**
 * UserLoginRequestModel
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class UserLoginRequestModel {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public UserLoginRequestModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginRequestModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
