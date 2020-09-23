/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootApplication
@ConfigurationProperties("auth-server")
public class Main implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Value("${test.boolean}")
    private Boolean testBoolean;

    @Value("${test.string}")
    private String testString;

    @Value("${test.integer}")
    private Integer testInteger;

    @Value("${test.long}")
    private Long testLong;

    @Value("${test.float}")
    private Float testFloat;

    @Value("${test.double}")
    private Double testDouble;

    @Value("#{'${test.array}'.split(',')}")
    private String[] testArray;

    @Value("#{'${test.list}'.split(',')}")
    private List<String> testList;

    @Value("#{'${test.set}'.split(',')}")
    private Set<String> testSet;

    @Value("#{${test.map}}")
    private Map<String, Object> testMap;

    private Map<String, String> cookieMap;

    public Map<String, String> getCookieMap() {
        return cookieMap;
    }

    public void setCookieMap(Map<String, String> cookieMap) {
        this.cookieMap = cookieMap;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("boolean=" + testBoolean);
        System.out.println("string=" + testString);
        System.out.println("integer=" + testInteger);
        System.out.println("long=" + testLong);
        System.out.println("float=" + testFloat);
        System.out.println("double=" + testDouble);
        System.out.println("array=" + Arrays.asList(testArray));
        System.out.println("set=" + testSet);
        System.out.println("list=" + testList);
        System.out.println("map=" + testMap);
        System.out.println("cookieMap=" + cookieMap);
    }
}
