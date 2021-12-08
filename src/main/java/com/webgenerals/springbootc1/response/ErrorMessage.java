package com.webgenerals.springbootc1.response;

import java.util.Date;

/**
 * ErrorMessage
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class ErrorMessage {

    private Date timestamp;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public ErrorMessage setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
