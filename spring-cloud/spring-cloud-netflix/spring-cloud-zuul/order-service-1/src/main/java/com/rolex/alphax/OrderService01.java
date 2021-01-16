/*
 * Copyright (C) 2018 bsyonline
 */
package com.rolex.alphax;

import com.google.common.collect.Lists;
import com.rolex.alphax.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author rolex
 * @since 2018
 */
@RefreshScope
@SpringBootApplication
@RestController
public class OrderService01 {
    public static void main(String[] args) {
        SpringApplication.run(OrderService01.class, args);
    }

    @Autowired
    HttpServletRequest request;

    @GetMapping("/hello")
    public String test() {
        return "test1:" + request.getRequestURI();
    }

    @GetMapping("/world")
    public String test2() {
        return "test2:" + request.getRequestURI();
    }

    @GetMapping("/hello/world")
    public String test3() {
        return "test3:" + request.getRequestURI();
    }

    @GetMapping("/a/world")
    public String test4() {
        return "test4:" + request.getRequestURI();
    }

    @GetMapping("/a/b/world")
    public String test5() {
        return "test5:" + request.getRequestURI();
    }

    @GetMapping("/a/world/b")
    public String test6() {
        return "test6:" + request.getRequestURI();
    }

    @GetMapping("/a/b/world/a/b")
    public String test7() {
        return "test7:" + request.getRequestURI();
    }

    @GetMapping("/haha")
    public String test8() {
        return "test8:" + request.getRequestURI();
    }

}
