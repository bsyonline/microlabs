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

    public static final String HELLO_EXCHANGE = "hello-exchange";
    public static final String HELLO_QUEUE = "hello-queue";
    public static final String HELLO_ROUTING_KEY = "#";

    public static final String DEAD_LETTER_EXCHANGE = "hello-dlx";
    public static final String DEAD_LETTER_REDIRECT_ROUTING_KEY = "hello-dl-key";
    public static final String DEAD_LETTER_QUEUE = "hello-dlq";

    //声明队列
    @Bean
    public Queue helloQueue() {
        return QueueBuilder.durable(HELLO_QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE) //声明死信队列Exchange
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_REDIRECT_ROUTING_KEY)//声明死信队列抛出异常重定向队列的routingKey
                .build();
    }

    //声明交互器
    @Bean
    TopicExchange helloExchange() {
        return new TopicExchange(HELLO_EXCHANGE);
    }

    //绑定
    @Bean
    public Binding helloBinding() {
        return BindingBuilder.bind(helloQueue()).to(helloExchange()).with(HELLO_ROUTING_KEY);
    }

}
