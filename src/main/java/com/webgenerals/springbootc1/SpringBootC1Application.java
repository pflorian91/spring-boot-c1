package com.webgenerals.springbootc1;

import com.webgenerals.springbootc1.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootC1Application {

    // extends SpringBootServletInitializer

    public static void main(String[] args) {
        SpringApplication.run(SpringBootC1Application.class, args);
    }

/*    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootC1Application.class);
    }*/

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }

    @Bean(name = "AppProperties")
    public AppProperties appProperties() {
        return new AppProperties();
    }
}
