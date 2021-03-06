/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rolex
 * @since 2019
 */
@SpringBootApplication
@RestController
@Slf4j
public class RibbonServer02 {
    public static void main(String[] args) {
        SpringApplication.run(RibbonServer02.class, args);
    }

    @GetMapping("/ribbon")
    public String ribbon(HttpServletRequest request) {
        log.info("uri {} called", "/ribbon");
        return "host=" + request.getRemoteHost() + ", port=" + request.getServerPort();
    }
}
