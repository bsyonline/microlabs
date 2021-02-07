/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
public final class GeneralGrantedAuthority {
    private final String role;
    private final List<SysPermission> permissions;

    public GeneralGrantedAuthority(String role, List<SysPermission> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

}
