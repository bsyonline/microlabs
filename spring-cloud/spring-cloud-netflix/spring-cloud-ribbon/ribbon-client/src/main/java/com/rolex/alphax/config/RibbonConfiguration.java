/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.rolex.alphax.loadbalancer.MyPing;
import com.rolex.alphax.loadbalancer.MyRibbonRule;
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
