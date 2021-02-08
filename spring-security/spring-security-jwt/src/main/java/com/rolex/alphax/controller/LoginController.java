/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.alphax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2021
 */
@RestController
public class LoginController {

    @GetMapping("/invalidSession")
    public String invalidSession(){
        return "invalidSession";
    }

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }
}
