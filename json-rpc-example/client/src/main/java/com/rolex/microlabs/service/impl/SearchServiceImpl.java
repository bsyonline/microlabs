/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.model.User;
import com.rolex.microlabs.service.SearchService;
import com.rolex.microlabs.service.UserService;
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
