/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2020
 */
@RestController
@SpringBootApplication
public class ApplicationService02 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationService02.class, args);
    }

    int count = 0;

    @GetMapping("/service2")
    public String service2() {
        if (count % 2 == 0) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        return "service2";
    }
}
