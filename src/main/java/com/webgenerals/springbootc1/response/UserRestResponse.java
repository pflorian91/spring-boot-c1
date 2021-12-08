package com.webgenerals.springbootc1.response;

/**
 * UserRestResponse
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class UserRestResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    public String getUserId() {
        return userId;
    }

    public UserRestResponse setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRestResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRestResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRestResponse setEmail(String email) {
        this.email = email;
        return this;
    }
}
