/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootApplication
@EnableDubbo
public class Business2Application {
    public static void main(String[] args) {
        SpringApplication.run(Business2Application.class, args);
    }
}
