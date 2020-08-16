/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.util.*;

/**
 * @author rolex
 * @since 2020
 */
public class Producer {
    public static void main(String[] args) throws IOException, MQClientException {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");

            producer.setNamesrvAddr("localhost:9876");

            producer.start();

            String[] tags = new String[]{"TagA", "TagC", "TagD"};

            // 订单列表
            Queue<Task> queue = new Producer().buildOrders();

            while (!queue.isEmpty()) {
                Task task = queue.poll();
                // 加个时间后缀
                Message msg = new Message("Topic-4", task.toString().getBytes());

                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Long id = (Long) arg;
                        long index = id % mqs.size();
                        System.out.print("MqQueueSize=" + mqs.size() + ", index=" + index + ", ");
                        return mqs.get((int) index);
                    }
                }, task.getUserId());

                System.out.println("Queue="+sendResult.getMessageQueue().getQueueId() + ", " + task);
            }

            producer.shutdown();

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成模拟订单数据
     */
    private Queue<Task> buildOrders() {
        Queue<Task> queue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o2.getPriority() - o1.getPriority();
            }
        });

        List<Long> userList = Arrays.asList(110L, 111L, 112L, 113L);

        for (int i = 0; i < 20; i++) {
            Task task1 = new Task();
            task1.setId(i);
            task1.setPriority(new Random().nextInt(4));
            task1.setName("t" + i);
            task1.setUserId(userList.get(i % userList.size()));
            queue.add(task1);
        }
        return queue;
    }
}
