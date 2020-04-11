/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.structural.adapter;

/**
 * @author rolex
 * @since 2020
 */
public class OuterUserAdapter extends OuterUserInfo implements IUserInfo {

    @Override
    public String getName() {
        return (String)basicInfo().get("name");
    }

    @Override
    public Integer getAge() {
        return Integer.parseInt((String)basicInfo().get("age"));
    }

    @Override
    public String getTechnical() {
        return (String)technicalInfo().get("language");
    }
}
