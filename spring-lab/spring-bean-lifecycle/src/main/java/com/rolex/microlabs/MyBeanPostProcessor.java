/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author rolex
 * @since 2020
 */
@Component("beanPostProcessor")
public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("com.rolex.microlabs.MyBeanPostProcessor.postProcessBeforeInitialization");
        if (bean instanceof Person) {
            Person p = (Person) bean;
            System.out.println("修改之前：name=" + p.name);
            p.name = "Alice";
            System.out.println("修改之后：name=" + p.name);
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("com.rolex.microlabs.MyBeanPostProcessor.postProcessAfterInitialization");
        if (bean instanceof Person) {
            Person p = (Person) bean;
            System.out.println("修改之前：name=" + p.name);
            p.name = "William";
            System.out.println("修改之后：name=" + p.name);
        }
        return bean;
    }
}
