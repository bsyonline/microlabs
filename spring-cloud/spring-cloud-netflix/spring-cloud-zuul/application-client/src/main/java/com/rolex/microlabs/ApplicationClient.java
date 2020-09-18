/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author rolex
 * @since 2020
 */
@SpringBootApplication
@RestController
public class ApplicationClient {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationClient.class, args);
    }


    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/test")
    public String test() {
        String host = "http://localhost:8088";
        StringBuffer sb = new StringBuffer();
        sb.append("/hello").append(" --> ").append( restTemplate.getForObject(host + "/hello", String.class)).append("<br>");
        sb.append("/hello/world").append(" --> ").append( restTemplate.getForObject(host + "/hello/world", String.class)).append("<br>");
        sb.append("/hello/*/world").append(" --> ").append( restTemplate.getForObject(host + "/hello/a/world", String.class)).append("<br>");
        sb.append("/hello/**/world").append(" --> ").append( restTemplate.getForObject(host + "/hello/a/b/world", String.class)).append("<br>");
        sb.append("/hello/*/world/*").append(" --> ").append( restTemplate.getForObject(host + "/hello/a/world/b", String.class)).append("<br>");
        sb.append("/hello/**/world/**").append(" --> ").append( restTemplate.getForObject(host + "/hello/a/b/world/a/b", String.class)).append("<br>");
        sb.append("/test").append(" --> ").append( restTemplate.getForObject(host + "/haha", String.class)).append("<br>");

        return sb.toString();
    }
}
