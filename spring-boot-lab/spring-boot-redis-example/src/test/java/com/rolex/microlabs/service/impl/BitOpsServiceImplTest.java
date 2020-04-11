package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.BitOpsService;
import com.rolex.microlabs.service.HashOpsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BitOpsServiceImplTest {

    @Autowired
    BitOpsService bitOpsService;

    @Test
    public void test01Add() {
        // 统计签到
        bitOpsService.setBit("sign_tom", 20200101, true);
        bitOpsService.setBit("sign_tom", 20200102, true);
        bitOpsService.setBit("sign_tom", 20200104, true);
        bitOpsService.setBit("sign_tom", 20200105, true);
        System.out.println(bitOpsService.bitCount("sign_tom"));

    }

}