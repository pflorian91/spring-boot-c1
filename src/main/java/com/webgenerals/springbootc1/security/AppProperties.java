package com.webgenerals.springbootc1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * AppProperties
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
@Component
public class AppProperties {

    @Autowired
    private Environment environment;

    String getTokenSecret() {
        return environment.getProperty("tokenSecret");
    }
}
