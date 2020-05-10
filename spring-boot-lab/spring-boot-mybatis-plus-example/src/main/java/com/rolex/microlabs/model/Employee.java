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
@TableName("t_employee")
public class Employee {
    Long id;
    String name;
    Integer age;
    String email;
    Long deptId;
}
