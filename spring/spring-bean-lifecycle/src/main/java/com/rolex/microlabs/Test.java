/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author rolex
 * @since 2020
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.rolex.microlabs");
        context.refresh();
        Person person = context.getBean(Person.class);
        System.out.println(person.name);
    }
}
