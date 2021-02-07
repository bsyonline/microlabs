/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.controller;

import com.rolex.alphax.service.UserService;
import com.rolex.alphax.service.bo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rolex
 * @since 2020
 */
@Controller
@RequestMapping("/sys/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{name}")
    public SysUser getUser(@PathVariable("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping
    public String list() {
        return "sys/user";
    }
}
