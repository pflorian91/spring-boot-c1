package com.webgenerals.springbootc1.model;

/**
 * PasswordResetModel
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class PasswordResetModel {

    private String token;
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
