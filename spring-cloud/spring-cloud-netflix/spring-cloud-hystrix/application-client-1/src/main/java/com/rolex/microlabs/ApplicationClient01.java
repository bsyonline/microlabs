/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author rolex
 * @since 2020
 */
@EnableCircuitBreaker
@RestController
@SpringBootApplication
@Slf4j
public class ApplicationClient01 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationClient01.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultFallback", commandProperties = {
//            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "100"),
//            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "1")
    })
    @GetMapping("/test")
    public String test() {
        return "client -> " + restTemplate.getForObject("http://application-service-1/service1", String.class);
    }

    public String defaultFallback(Throwable e) {
        log.error("service1 fallback");
        return "service1(none)";
    }
}
