/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rolex.alphax.dao.entity.*;
import com.rolex.alphax.dao.mapper.*;
import com.rolex.alphax.service.UserService;
import com.rolex.alphax.service.bo.GeneralGrantedAuthority;
import com.rolex.alphax.service.bo.SysPermission;
import com.rolex.alphax.service.bo.SysRole;
import com.rolex.alphax.service.bo.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
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
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        BeanUtils.copyProperties(user, sysUser);
        List<UserRole> userRoles = userRoleMapper.selectList(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId()));
        if (!userRoles.isEmpty()) {
            Set<Long> roleIds = userRoles.stream().map(r -> r.getRoleId()).collect(Collectors.toSet());
            List<Role> roles = roleMapper.selectBatchIds(roleIds);
            sysUser.setRoles(roles.stream().map(r -> {
                SysRole sysRole = new SysRole();
                BeanUtils.copyProperties(r, sysRole);
                return sysRole;
            }).collect(Collectors.toList()));
            List<RolePermission> rolePermissions = rolePermissionMapper.selectList(new LambdaQueryWrapper<RolePermission>().in(RolePermission::getRoleId, roleIds));
            if (!rolePermissions.isEmpty()) {
                Set<Long> permissionIds = rolePermissions.stream().map(r -> r.getPermissionId()).collect(Collectors.toSet());
                List<Permission> permissions = permissionMapper.selectBatchIds(permissionIds);
                Map<Long, List<Permission>> permissionMap = permissions.stream().collect(Collectors.groupingBy(Permission::getId));
                Map<Long, List<RolePermission>> map = rolePermissions.stream().collect(Collectors.groupingBy(RolePermission::getRoleId));
                Map<Long, List<SysPermission>> map1 = new HashMap<>();
                map.forEach((roleId, userRoleList) -> {
                    List<SysPermission> list = new ArrayList<>();
                    userRoleList.stream().map(RolePermission::getPermissionId).map(permissionId -> permissionMap.get(permissionId).get(0)).forEach(p -> {
                        SysPermission sysPermission = new SysPermission();
                        BeanUtils.copyProperties(p, sysPermission);
                        list.add(sysPermission);
                    });
                    map1.put(roleId, list);
                });
                sysUser.getRoles().forEach(role -> {
                    Long id = role.getId();
                    role.setPermissions(map1.get(id));
                });
            }
        }
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = getUserByName(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException(username);
        }
        List<GeneralGrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : sysUser.getRoles()) {
            authorities.add(new GeneralGrantedAuthority(role.getCode(), role.getPermissions()));
        }
        return new org.springframework.security.core.userdetails.User(sysUser.getUsername(),
                sysUser.getPassword(),
                sysUser.getState() == 1,
                sysUser.getAccountExpired() == 0,
                sysUser.getAccountExpired() == 0,
                sysUser.getAccountLocked() == 0,
                authorities);
    }
}
