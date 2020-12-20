/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2020
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public String list() {
        return "product list";
    }

}
