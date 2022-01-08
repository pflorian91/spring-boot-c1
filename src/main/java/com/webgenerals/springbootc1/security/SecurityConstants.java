package com.webgenerals.springbootc1.security;

import com.webgenerals.springbootc1.SpringApplicationContext;

/**
 * SecurityConstants
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class SecurityConstants {

    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final long PASSWORD_RESET_EXPIRATION_TIME = 3600000; // 1 hour
    static final String BEARER_TOKEN_PREFIX = "Bearer ";
    static final String HEADER_AUTHORIZATION = "Authorization";
    static final String SIGN_UP_URL = "/users";
    static final String VERIFICATION_EMAIL_URL = "/users/email-verification";
    static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request";
    static final String PASSWORD_RESET_URL = "/users/password-reset";

    public static String getTokenSecret() {
        AppProperties properties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return properties.getTokenSecret();
    }

}
