/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author rolex
 * @since 2020
 */
@Data
@TableName("t_user")
public class User {
    Long id;
    String name;
}
