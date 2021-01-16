/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax;

import com.rolex.alphax.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author rolex
 * @since 2020
 */
@RestController
@EnableFeignClients
@SpringBootApplication
public class ApplicationClient01 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationClient01.class, args);
    }

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/test")
    public String test() {
        return applicationService.getZone();
    }
}
