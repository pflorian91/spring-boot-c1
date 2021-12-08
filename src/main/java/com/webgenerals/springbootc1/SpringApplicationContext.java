package com.webgenerals.springbootc1;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * SpringApplicationContext
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }
}
