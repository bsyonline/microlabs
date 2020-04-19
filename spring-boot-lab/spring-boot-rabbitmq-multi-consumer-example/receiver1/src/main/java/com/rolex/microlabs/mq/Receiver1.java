/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.microlabs.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.rolex.microlabs.config.RabbitmqConfig.MULTI_QUEUE;

/**
 * @author rolex
 * @since 2019
 */
@Component
@Slf4j
public class Receiver1 {

    @RabbitListener(queues = MULTI_QUEUE)
    public void processMessage(String msg, Channel channel, Message message) {
        log.info("Receiver1-{}接收到来自{}队列的消息：{}", Thread.currentThread().getName(), MULTI_QUEUE, msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("消费成功，返回ACK");
        } catch (Exception e) {
            throw new RuntimeException("消费失败:" + e.getMessage());
        }
    }

}
