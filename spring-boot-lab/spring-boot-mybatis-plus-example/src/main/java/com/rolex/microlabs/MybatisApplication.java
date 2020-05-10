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
@MapperScan("com.rolex.microlabs.dao")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
