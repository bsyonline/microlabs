package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.ListOpsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.Oneway;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ListOpsServiceImplTest {

    @Autowired
    ListOpsService listOpsService;

    @Test
    public void test01Add() {
        listOpsService.lpush("list1", "A");
        listOpsService.lpush("list1", "B");
        listOpsService.lpush("list1", "C");
        System.out.println(Arrays.toString(listOpsService.lrange("list1", 0, 2).toArray()));
        listOpsService.lset("list1", 0, "a");
        listOpsService.lset("list1", 1, "b");
        listOpsService.lset("list1", 2, "c");
        System.out.println(Arrays.toString(listOpsService.lrange("list1", 0, 2).toArray()));
        listOpsService.rpush("list1", "X");
        listOpsService.rpush("list1", "Y");
        listOpsService.rpush("list1", "Z");
        System.out.println(Arrays.toString(listOpsService.lrange("list1", 0, 5).toArray()));
        System.out.println(listOpsService.lpop("list1"));
        System.out.println(listOpsService.rpop("list1"));
        while (listOpsService.llen("list1") > 0) {
            listOpsService.lpop("list1");
        }

    }

}