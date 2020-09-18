/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerPing;
import com.netflix.loadbalancer.Server;

import java.time.LocalTime;

/**
 * @author rolex
 * @since 2020
 */
public class MyPing extends AbstractLoadBalancerPing {

    @Override
    public boolean isAlive(Server server) {
        return LocalTime.now().isBefore(LocalTime.of(23,0));
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        LocalTime.now();
    }

}
