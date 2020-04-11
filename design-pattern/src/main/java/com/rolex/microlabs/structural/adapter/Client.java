/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.adapter;

/**
 * @author rolex
 * @since 2020
 */
public class Client {
    public static void main(String[] args) {
        IUserInfo userInfo = new UserInfo();
        userInfo.getName();
        userInfo.getAge();
        userInfo.getTechnical();

        System.out.println("--");
        IUserInfo adapter = new OuterUserAdapter();
        System.out.println("name is " + adapter.getName());
        System.out.println("age is " + adapter.getAge());
        System.out.println("language is " + adapter.getTechnical());
    }
}
