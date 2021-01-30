/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
public class SysPermission implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String url;
    private Long parentId;
}
