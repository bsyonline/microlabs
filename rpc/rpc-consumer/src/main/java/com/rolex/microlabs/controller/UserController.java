/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.controller;

import com.rolex.microlabs.model.User;
import com.rolex.microlabs.netty.NettyClient;
import com.rolex.microlabs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2020
 */
@RestController
public class UserController {

    @Autowired
    NettyClient nettyClient;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(name = "id") int id) {
        UserService userService = (UserService) nettyClient.getInstance(UserService.class, "v1.0");
        User user = userService.getUser(id);
        return user;
    }
}
