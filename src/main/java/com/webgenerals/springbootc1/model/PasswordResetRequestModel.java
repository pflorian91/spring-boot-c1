package com.webgenerals.springbootc1.model;

/**
 * PasswordResetRequestModel
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class PasswordResetRequestModel {

    private String email;

    public String getEmail() {
        return email;
    }

    public PasswordResetRequestModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
