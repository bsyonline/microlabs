/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.*;

/**
 * @author rolex
 * @since 2020
 */
public class PullConsumer {
    private static final Long OFFSET= 4L;

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("please_rename_unique_group_name_3");
        consumer.setNamesrvAddr("localhost:9876");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.start();
        // 1. 获取Topic的所有队列
        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues("Topic-2");
        MessageQueue mq = new ArrayList<>(messageQueues).get(0);
        PullResult pullResult = consumer.pull(mq, "*", OFFSET, 2);
        for (MessageExt msg : pullResult.getMsgFoundList()) {
            System.out.println(msg.getQueueId() +", " + new String(msg.getBody()));
        }

        // 3. 关闭Consumer
        consumer.shutdown();
    }

}
