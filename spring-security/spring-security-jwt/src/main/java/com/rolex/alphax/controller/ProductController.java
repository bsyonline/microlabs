/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rolex
 * @since 2020
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String list() {
        return "product/product";
    }

}
