/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.alphax.config;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.rolex.alphax.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2021
 */
@Configuration
public class JsonRpcConfig {
    private static final String endpoint = "http://localhost:8080/users";

    @Bean
    public JsonRpcHttpClient jsonRpcHttpClient() {
        URL url = null;
        // You can add authentication headers etc to this map
        Map<String, String> map = new HashMap<String, String>();
        try {
            url = new URL(endpoint);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JsonRpcHttpClient(url, map);
    }

    @Bean
    public UserService userService(JsonRpcHttpClient jsonRpcHttpClient) {
        return ProxyUtil.createClientProxy(getClass().getClassLoader(), UserService.class, jsonRpcHttpClient);
    }
}
