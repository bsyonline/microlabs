/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.mq;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rolex
 * @since 2020
 */
@Setter
@Getter
public class CorrelationDataWrapper extends org.springframework.amqp.rabbit.connection.CorrelationData {
    //消息体
    private volatile Object message;
    //交换机
    private String exchange;
    //路由键
    private String routingKey;
    //重试次数
    private int retryCount = 0;
}
