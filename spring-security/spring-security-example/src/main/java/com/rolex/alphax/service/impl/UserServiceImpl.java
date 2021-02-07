/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.impl;

import com.rolex.alphax.dao.mapper.*;
import com.rolex.alphax.service.UserService;
import com.rolex.alphax.service.bo.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rolex
 * @since 2020
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public SysUser getUserByName(String username) {
        SysUser sysUser = new SysUser();

        return sysUser;
    }

    @Override
    public List<SysUser> list() {
        return userMapper.selectList(null).stream().map(u -> {
            SysUser sysUser = new SysUser();
            BeanUtils.copyProperties(u, sysUser);
            return sysUser;
        }).collect(Collectors.toList());
    }

}
