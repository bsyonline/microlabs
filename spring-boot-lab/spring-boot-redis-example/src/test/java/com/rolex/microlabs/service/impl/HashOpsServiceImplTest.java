package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.HashOpsService;
import com.rolex.microlabs.service.ListOpsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HashOpsServiceImplTest {

    @Autowired
    HashOpsService hashOpsService;

    @Test
    public void test01Add() {
        hashOpsService.set("user", "name", "tom");
        hashOpsService.set("user", "age", "20");
        hashOpsService.set("user", "gender", "male");
        System.out.println(hashOpsService.get("user", "name"));
        Set keys = hashOpsService.keys("user");
        System.out.println(Arrays.toString(keys.toArray()));
        System.out.println(Arrays.toString(hashOpsService.multiGet("user", keys).toArray()));
        hashOpsService.remove("user", keys);
    }

}