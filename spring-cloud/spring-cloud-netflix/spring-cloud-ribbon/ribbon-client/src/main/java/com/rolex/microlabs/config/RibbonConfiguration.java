/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.microlabs.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.rolex.microlabs.loadbalancer.MyPing;
import com.rolex.microlabs.loadbalancer.MyRibbonRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rolex
 * @since 2019
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new MyRibbonRule();
    }

    @Bean
    public IPing ribbonPing() {
        return new MyPing();
    }
}
