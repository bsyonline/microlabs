/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rolex
 * @since 2019
 */
@Configuration
public class RabbitmqConfig {

    public static final String MULTI_EXCHANGE = "multi-exchange";
    public static final String MULTI_QUEUE = "multi-queue1";
    public static final String MULTI_ROUTING_KEY = "multi-routingKey";

    //声明队列
    @Bean
    public Queue multiQueue() {
        return new Queue(MULTI_QUEUE);
    }

    //声明交互器
    @Bean
    TopicExchange multiExchange() {
        return new TopicExchange(MULTI_EXCHANGE);
    }

    //绑定
    @Bean
    public Binding multiBinding() {
        return BindingBuilder.bind(multiQueue()).to(multiExchange()).with(MULTI_ROUTING_KEY);
    }

}
