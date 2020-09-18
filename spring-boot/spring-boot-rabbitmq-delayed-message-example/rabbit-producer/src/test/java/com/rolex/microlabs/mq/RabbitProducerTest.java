package com.rolex.microlabs.mq;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
    public void sendDelayMsg() {
        rabbitProducer.sendDelayMsg("delay msg test " + new Date());
    }
}