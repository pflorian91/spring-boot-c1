package com.webgenerals.springbootc1.security;

import com.webgenerals.springbootc1.SpringApplicationContext;

/**
 * SecurityConstants
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
class SecurityConstants {

    static final long EXPIRATION_TIME = 864000000; // 10 days
    static final String BEARER_TOKEN_PREFIX = "Bearer ";
    static final String HEADER_AUTHORIZATION = "Authorization";
    static final String SING_UP_URL = "/users";

    public static String getTokenSecret() {
        AppProperties properties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return properties.getTokenSecret();
    }

}
