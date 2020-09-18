/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author rolex
 * @since 2020
 */
@EnableEurekaServer
@SpringBootApplication
public class Eureka02Server {
    public static void main(String[] args) {
        SpringApplication.run(Eureka02Server.class, args);
    }
}
