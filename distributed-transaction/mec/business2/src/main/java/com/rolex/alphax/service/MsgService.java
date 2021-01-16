/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service;

import com.rabbitmq.client.Channel;
import com.rolex.alphax.model.TxMsg;
import com.rolex.alphax.service.TxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.rolex.alphax.config.RabbitmqConfig.MULTI_QUEUE;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
@Component
public class MsgService {

    @Reference(version = "1.0.0")
    TxService txService;

    @RabbitListener(queues = MULTI_QUEUE)
    public void processMessage(TxMsg msg) throws Exception {
        log.info("Receiver-{}接收到来自{}队列的消息：{}", Thread.currentThread().getName(), MULTI_QUEUE, msg);
        txService.deleteMsg(msg.getMsgId());
    }
}
