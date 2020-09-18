/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootApplication
public class RabbitConsumer01Application {
    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumer01Application.class, args);
    }
}
