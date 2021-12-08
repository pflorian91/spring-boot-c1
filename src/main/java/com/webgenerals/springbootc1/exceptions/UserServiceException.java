package com.webgenerals.springbootc1.exceptions;

/**
 * UserServiceException
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 5360226659118936864L;

    public UserServiceException(String message) {
        super(message);
    }
}
