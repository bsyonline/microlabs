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
public class RabbitPublisherTest {

    @Autowired
    RabbitPublisher rabbitPublisher;

    @Test
    public void sendToDirect() {
        rabbitPublisher.sendToDirect("test direct msg");
    }
}