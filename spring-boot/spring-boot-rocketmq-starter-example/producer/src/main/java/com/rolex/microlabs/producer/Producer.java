/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.producer;

import com.rolex.microlabs.model.OrderPaidEvent;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        //send message synchronously
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
        //send spring message
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, Spring!").build());
        rocketMQTemplate.send("test-topic-2", MessageBuilder.withPayload(new OrderPaidEvent("T_001", new BigDecimal("88.00"))).build());
        //send message asynchronously
        rocketMQTemplate.asyncSend("test-topic-1", "Asynchronous Message", new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });
        //Send messages orderly
        rocketMQTemplate.syncSendOrderly("orderly_topic", MessageBuilder.withPayload("Ordered Msg").build(), "hashkey");

        rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
    }

}
