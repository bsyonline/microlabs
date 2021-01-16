/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.alphax.controller;

import com.rolex.alphax.model.User;
import com.rolex.alphax.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rolex
 * @since 2021
 */
@RestController
public class UserController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/hello")
    public List<User> index() {
        List<User> list = searchService.search();
        return list;
    }
}
