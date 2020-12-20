/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.controller;

import com.rolex.microlabs.service.UserService;
import com.rolex.microlabs.service.bo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{name}")
    public SysUser getUser(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping
    public List<SysUser> list() {
        return userService.list();
    }
}
