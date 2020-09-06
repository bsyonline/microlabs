/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.microlabs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rolex
 * @since 2019
 */
@SpringBootApplication
@MapperScan("com.rolex.microlabs.dao")
public class ReadWriteSplitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadWriteSplitApplication.class, args);
    }
}
