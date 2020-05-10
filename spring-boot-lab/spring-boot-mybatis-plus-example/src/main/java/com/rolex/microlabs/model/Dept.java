/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author rolex
 * @since 2020
 */
@Data
@TableName("t_dept")
public class Dept {
    @TableId("dept_id")
    Long id;
    @TableField("dept_name")
    String name;
}
