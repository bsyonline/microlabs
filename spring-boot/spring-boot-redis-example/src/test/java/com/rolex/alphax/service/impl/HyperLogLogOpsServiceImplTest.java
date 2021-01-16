package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.HyperLogLogOpsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HyperLogLogOpsServiceImplTest {

    @Autowired
    HyperLogLogOpsService hyperLogLogOpsService;

    @Test
    public void test01Add() {
        hyperLogLogOpsService.add("uv20200101", "1");
        hyperLogLogOpsService.add("uv20200101", "3");
        hyperLogLogOpsService.add("uv20200101", "2");
        hyperLogLogOpsService.add("uv20200101", "2");
        System.out.println(hyperLogLogOpsService.count("uv20200101"));
        hyperLogLogOpsService.add("uv20200102", "1");
        hyperLogLogOpsService.add("uv20200102", "3");
        hyperLogLogOpsService.add("uv20200102", "2");
        hyperLogLogOpsService.add("uv20200102", "1");
        System.out.println(hyperLogLogOpsService.count("uv20200102"));
        hyperLogLogOpsService.merge("uv", "uv20200101", "uv20200102");
        System.out.println(hyperLogLogOpsService.count("uv"));

    }

}