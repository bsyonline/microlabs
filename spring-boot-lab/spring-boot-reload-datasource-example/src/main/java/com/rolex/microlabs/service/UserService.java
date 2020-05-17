/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;

import com.rolex.microlabs.dao.UserDao;
import com.rolex.microlabs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class UserService {
    @Autowired
    UserDao userDao;

    public List<User> findAll(){
        return userDao.selectList(null);
    }
}
