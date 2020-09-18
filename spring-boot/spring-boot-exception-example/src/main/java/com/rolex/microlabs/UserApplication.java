/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootApplication
@MapperScan("com.rolex.microlabs.employee.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
