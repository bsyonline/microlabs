/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2020
 */
@NacosPropertySource(dataId = "spring-boot-nacos-example.yml", groupId = "spring-boot-nacos-example", autoRefreshed = true)
@SpringBootApplication
@RestController
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }

    @NacosValue(value = "${name:tom}", autoRefreshed = true)
    private String name;

    @GetMapping("/get")
    public String get(){
        return "hello, " + name;
    }

}
