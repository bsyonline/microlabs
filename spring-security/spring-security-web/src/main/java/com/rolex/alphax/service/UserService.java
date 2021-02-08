package com.rolex.alphax.service;

import com.rolex.alphax.service.bo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
public interface UserService extends UserDetailsService {

    SysUser getUserByName(String username);

    List<SysUser> list();
}
