/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.alphax.service.impl;

import com.rolex.alphax.model.User;
import com.rolex.alphax.service.SearchService;
import com.rolex.alphax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rolex
 * @since 2021
 */
@Component
public class SearchServiceImpl implements SearchService {
    @Autowired
    UserService userService;


    @Override
    public List<User> search() {
        return userService.getUser("name");
    }
}
