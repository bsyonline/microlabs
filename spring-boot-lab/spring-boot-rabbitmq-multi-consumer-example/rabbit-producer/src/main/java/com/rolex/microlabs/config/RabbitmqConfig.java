/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.microlabs.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rolex
 * @since 2019
 */
@Configuration
public class RabbitmqConfig {

    /**
     * direct 方式
     */
    public static final String DIRECT_EXCHANGE = "sample.direct111";
    public static final String DIRECT_QUEUE = "sample.direct.queue";
    public static final String DIRECT_ROUTING_KEY = "sampleKey";




}
