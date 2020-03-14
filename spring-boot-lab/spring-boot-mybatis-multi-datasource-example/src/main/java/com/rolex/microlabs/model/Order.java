/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.model;

import lombok.Data;

import java.util.Date;

/**
 * @author rolex
 * @since 2020
 */
@Data
public class Order {
    Integer orderNo;
    Integer userId;
    Date createTime;
}
