/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author rolex
 * @since 2020
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.rolex.alphax");
        context.refresh();
        Person person = context.getBean(Person.class);
        System.out.println(person.name);
    }
}
