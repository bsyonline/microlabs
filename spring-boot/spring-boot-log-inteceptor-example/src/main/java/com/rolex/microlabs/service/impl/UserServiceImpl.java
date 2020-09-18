/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.log.Log;
import com.rolex.microlabs.model.User;
import com.rolex.microlabs.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@Service
public class UserServiceImpl implements UserService{

//    @Autowired
//    UserService userService;

    @Override
    @Log
    public String get(Long id){
        System.out.println("service: user get");
        Set<Long> ids = new HashSet<>();
        ids.add(1000L);
        ids.add(1001L);
        ids.add(1002L);
        findAll(ids);
        update(new User());
//        userService.update(new User());
//        ((UserService) AopContext.currentProxy()).update(new User());
        return "OK";
    }

    @Override
    @Log
    public String update(User user){
        System.out.println("service: user update");
        return "OK";
    }

    @Log
    private Set<Long> findAll(Set<Long> ids){
        return new HashSet<>();
    }

}
