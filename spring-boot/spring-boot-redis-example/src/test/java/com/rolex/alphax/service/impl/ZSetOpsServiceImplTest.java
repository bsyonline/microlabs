package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.ZSetOpsService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZSetOpsServiceImplTest {

    @Autowired
    ZSetOpsService zSetOpsService;

    @Test
    public void test01Add() {
        zSetOpsService.add("zset1", "A", 1.0);
        zSetOpsService.add("zset1", "B", 2.0);
        zSetOpsService.add("zset1", "C", 3.0);
        zSetOpsService.add("zset1", "D", 1.0);
        System.out.println(Arrays.toString(zSetOpsService.range("zset1", 0, 3).toArray()));
        System.out.println("B rank is " + zSetOpsService.rank("zset1", "B"));
        System.out.println("B score is " + zSetOpsService.score("zset1", "B"));
        zSetOpsService.remove("zset1", "A", "B", "C", "D");
    }

    @Test
    public void test02Add() {
    }

    @Test
    public void test03Range() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void rank() {
    }

    @Test
    public void score() {
    }
}