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

    public static final String DEAD_LETTER_EXCHANGE = "world-dlx";
    public static final String DEAD_LETTER_REDIRECT_ROUTING_KEY = "world-dl-key";
    public static final String DEAD_LETTER_QUEUE = "world-dlq";

    public static final String WORLD_EXCHANGE = "world-exchange";
    public static final String WORLD_QUEUE = "world-queue";
    public static final String WORLD_ROUTING_KEY = "#";

    public static final String DELAY_EXCHANGE_NAME = "world-delay-exchange";
    public static final String DELAY_QUEUE = "world-delay-queue";
    public static final String DELAY_ROUTING_KEY = "#";

    //声明死信队列
    @Bean
    public Queue deadLetterQueue() {
        return new Queue(DEAD_LETTER_QUEUE, true);
    }

    @Bean
    public TopicExchange dlxExchange() {
        return new TopicExchange(DEAD_LETTER_EXCHANGE);
    }

    //绑定dlq
    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(dlxExchange()).with(DEAD_LETTER_REDIRECT_ROUTING_KEY);
    }

    @Bean
    public Queue worldQueue() {
        return QueueBuilder.durable(WORLD_QUEUE)
                .withArgument("x-dead-letter-exchange", DELAY_EXCHANGE_NAME) //声明死信队列Exchange
                .withArgument("x-dead-letter-routing-key", DELAY_ROUTING_KEY)//声明死信队列抛出异常重定向队列的routingKey
                .withArgument("x-message-ttl", 10 * 1000)
                .build();
    }

    @Bean
    public TopicExchange worldExchange() {
        return new TopicExchange(WORLD_EXCHANGE);
    }

    //绑定dlq
    @Bean
    public Binding worldBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(dlxExchange()).with(WORLD_ROUTING_KEY);
    }

    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable(DELAY_QUEUE)
                .build();
    }

    //声明交互器
    @Bean
    TopicExchange delayExchange() {
        return new TopicExchange(DELAY_EXCHANGE_NAME);
    }

    //绑定
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }

}
