package com.rolex.alphax.service;

import com.rolex.alphax.service.bo.SysUser;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
public interface UserService {

    SysUser getUserByName(String username);

    List<SysUser> list();
}
