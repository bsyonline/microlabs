/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.consumer;

import com.rolex.alphax.model.OrderPaidEvent;
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
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "group2",
        messageModel = MessageModel.BROADCASTING,consumeMode = ConsumeMode.CONCURRENTLY)
public class Consumer2 implements RocketMQListener<OrderPaidEvent> {
    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        log.info("received orderPaidEvent: {}", orderPaidEvent);
    }
}
