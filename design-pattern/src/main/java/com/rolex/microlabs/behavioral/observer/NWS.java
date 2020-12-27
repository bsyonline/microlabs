/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.behavioral.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class NWS implements WeatherObserver {
    @Override
    public void update(String args) {
        log.info("美国气象局观察到{}", args);
    }
}