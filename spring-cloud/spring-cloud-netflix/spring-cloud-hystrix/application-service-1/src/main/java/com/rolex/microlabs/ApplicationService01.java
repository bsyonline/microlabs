/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import com.rolex.microlabs.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2020
 */
@EnableCircuitBreaker
@EnableFeignClients
@RestController
@SpringBootApplication
public class ApplicationService01 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationService01.class, args);
    }

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/service1")
    public String service1(){
        return "service1 -> " + applicationService.service2();
    }
}
