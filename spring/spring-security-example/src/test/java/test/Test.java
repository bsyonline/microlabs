/*
 * Copyright (C) 2020 bsyonline
 */
package test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author rolex
 * @since 2020
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
