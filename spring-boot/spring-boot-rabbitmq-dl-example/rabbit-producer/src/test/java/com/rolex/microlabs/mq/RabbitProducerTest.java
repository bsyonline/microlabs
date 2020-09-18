package com.rolex.microlabs.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitProducerTest {

    @Autowired
    RabbitProducer rabbitProducer;

    @Test
    public void sendDeadLetterMsg() {
        for(int i=0;i<3;i++) {
            rabbitProducer.sendMsg("dead letter msg test " + i);
        }
    }
}