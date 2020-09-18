/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.controller;

import com.rolex.microlabs.model.User;
import com.rolex.microlabs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/test/{type}")
    public List<User> findAll(@PathVariable("type") String type) {
        log.info("{}", type);
        List<User> users = userService.findAll();
        return users;
    }

}
