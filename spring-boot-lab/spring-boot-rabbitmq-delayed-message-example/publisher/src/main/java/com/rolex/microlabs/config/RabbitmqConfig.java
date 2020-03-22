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

    public static final String WORLD_EXCHANGE = "world-exchange";
    public static final String WORLD_QUEUE = "world-queue";
    public static final String WORLD_ROUTING_KEY = "#";


    public static final String DELAY_EXCHANGE_NAME = "world-delay-exchange";
    public static final String DELAY_QUEUE = "world-delay-queue";
    public static final String DELAY_ROUTING_KEY = "#";


    @Bean
    public Queue worldQueue() {
        return QueueBuilder.durable(WORLD_QUEUE)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) //声明死信队列Exchange
                .withArgument("x-dead-letter-routing-key", DELAY_ROUTING_KEY)//声明死信队列抛出异常重定向队列的routingKey
                .withArgument("x-message-ttl", 10 * 1000)
                .build();
    }

    //声明交互器
    @Bean
    TopicExchange worldExchange() {
        return new TopicExchange(WORLD_EXCHANGE);
    }

    //绑定
    @Bean
    public Binding worldBinding() {
        return BindingBuilder.bind(worldQueue()).to(worldExchange()).with(WORLD_ROUTING_KEY);
    }

}
