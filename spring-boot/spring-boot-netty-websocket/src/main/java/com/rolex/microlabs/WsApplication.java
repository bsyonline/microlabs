/*
 * Copyright (C) 2021 bsyonline
 */
package com.rolex.microlabs;

import com.rolex.microlabs.server.WsServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author rolex
 * @since 2021
 */
@SpringBootApplication
public class WsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
        try {
            new WsServer(1234).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
