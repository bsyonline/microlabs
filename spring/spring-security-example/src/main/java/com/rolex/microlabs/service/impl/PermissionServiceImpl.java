/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.PermissionService;
import com.rolex.microlabs.service.UserService;
import com.rolex.microlabs.service.bo.GeneralGrantedAuthority;
import com.rolex.microlabs.service.bo.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class PermissionServiceImpl implements PermissionService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            for (GrantedAuthority p : ((UserDetails) principal).getAuthorities()) {
                List<SysPermission> permissions = ((GeneralGrantedAuthority) p).getPermissions();
                for (SysPermission permission : permissions) {
                    if (antPathMatcher.match(permission.getUrl(), request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            }
        }
        return hasPermission;
    }

}
