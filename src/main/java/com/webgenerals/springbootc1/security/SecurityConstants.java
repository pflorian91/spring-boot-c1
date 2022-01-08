package com.webgenerals.springbootc1.security;

import com.webgenerals.springbootc1.SpringApplicationContext;

/**
 * SecurityConstants
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class SecurityConstants {

    public static final long EXPIRATION_TIME = 864000000; // 10 days
    static final String BEARER_TOKEN_PREFIX = "Bearer ";
    static final String HEADER_AUTHORIZATION = "Authorization";
    static final String SIGN_UP_URL = "/users";
    static final String VERIFICATION_EMAIL_URL = "/users/email-verification";

    public static String getTokenSecret() {
        AppProperties properties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return properties.getTokenSecret();
    }

}
