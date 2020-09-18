package com.rolex.microlabs.service.impl;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetOpsServiceImplTest {
    @Autowired
    private SetOpsServiceImpl setOpsService;

    @Before
    public void init() {

    }

    @Test
    public void test01Add() {
        setOpsService.sadd("set1", "A", "B", "C");
        Set<String> set1 = setOpsService.smembers("set1");
        set1.forEach(v -> System.out.print(v + " "));
        setOpsService.srem("set1", "A", "B", "C");
    }

    @Test
    public void test02SetInt() {
        setOpsService.sadd("set1", "A", "B", "C");
        setOpsService.srem("set2", "B", "C", "D");
        System.out.println(Arrays.toString(setOpsService.sdiff("set1", "set2").toArray()));
        System.out.println(Arrays.toString(setOpsService.sunion("set1", "set2").toArray()));
        System.out.println(Arrays.toString(setOpsService.sinter("set1", "set2").toArray()));
        setOpsService.srem("set1", "A", "B", "C");
        setOpsService.srem("set2", "B", "C", "D");
    }
}