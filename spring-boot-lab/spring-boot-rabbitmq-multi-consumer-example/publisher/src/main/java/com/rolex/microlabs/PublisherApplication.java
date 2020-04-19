/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import com.rolex.microlabs.mq.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2020
 */
@RestController
@SpringBootApplication
public class PublisherApplication {
    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);
    }

    @Autowired
    Publisher publisher;

    @GetMapping("/{msg}")
    public String msg(@PathVariable String msg) {
        publisher.sendMsg(msg);
        return "ok";
    }
}
