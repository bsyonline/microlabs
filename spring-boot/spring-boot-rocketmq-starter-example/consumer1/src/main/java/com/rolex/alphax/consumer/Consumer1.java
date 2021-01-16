/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author rolex
 * @since 2020
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "group1",
        messageModel = MessageModel.BROADCASTING,consumeMode = ConsumeMode.CONCURRENTLY)
public class Consumer1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        log.info("received msg: {}", msg);
    }
}
