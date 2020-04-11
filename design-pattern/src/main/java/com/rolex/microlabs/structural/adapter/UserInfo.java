/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.adapter;

/**
 * @author rolex
 * @since 2020
 */
public class UserInfo implements IUserInfo {
    @Override
    public String getName() {
        System.out.println("username is tom");
        return null;
    }

    @Override
    public Integer getAge() {
        System.out.println("age is 20");
        return null;
    }

    @Override
    public String getTechnical() {
        System.out.println("language is java");
        return null;
    }
}
