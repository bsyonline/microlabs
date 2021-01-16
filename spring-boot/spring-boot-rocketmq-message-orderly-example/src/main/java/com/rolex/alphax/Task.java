/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author rolex
 * @since 2020
 */
@Data
public class Task {
    private long id;
    private int priority;
    private String name;
    private long userId;
    private long createTime = LocalDateTime.now().atZone(ZoneOffset.systemDefault()).toEpochSecond();
}
