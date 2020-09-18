/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Autowired
    DefaultMQPullConsumer consumer;
    @Bean
    public DefaultMQPullConsumer defaultMQPullConsumer() throws MQClientException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("please_rename_unique_group_name_3");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.start();
        return consumer;
    }

    @GetMapping("/getJob")
    public List get(int id, int offset,int size) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        List<String> list = new ArrayList<>();
        // 1. 获取Topic的所有队列
        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues("Topic-4");
        MessageQueue mq = new ArrayList<>(messageQueues).get(id);
        PullResult pullResult = consumer.pull(mq, "*", offset, size);
        for (MessageExt msg : pullResult.getMsgFoundList()) {
            list.add(msg.getQueueId() +", " + new String(msg.getBody()));
        }
        return list;
    }

}
