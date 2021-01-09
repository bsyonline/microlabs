/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.rolex.microlabs.model.User;
import com.rolex.microlabs.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rolex
 * @since 2021
 */
@Component
@AutoJsonRpcServiceImpl
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUser(String name) {
        System.out.println("name=" + name);
        List<User> list = new ArrayList<>();
        list.add(new User(1, "tom"));
        list.add(new User(2, "alice"));
        return list;
    }
}
