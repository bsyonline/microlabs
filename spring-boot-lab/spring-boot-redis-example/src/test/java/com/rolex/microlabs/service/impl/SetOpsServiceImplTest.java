package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.StringOpsService;
import junit.framework.TestCase;
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
        setOpsService.add("set1", "A", "B", "C");
        Set<String> set1 = setOpsService.get("set1");
        set1.forEach(v -> System.out.print(v + " "));
        setOpsService.remove("set1", "A", "B", "C");
    }

    @Test
    public void test02SetInt() {
        setOpsService.add("set1", "A", "B", "C");
        setOpsService.add("set2", "B", "C", "D");
        System.out.println(Arrays.toString(setOpsService.difference("set1", "set2").toArray()));
        System.out.println(Arrays.toString(setOpsService.union("set1", "set2").toArray()));
        System.out.println(Arrays.toString(setOpsService.intersect("set1", "set2").toArray()));
        setOpsService.remove("set1", "A", "B", "C");
        setOpsService.remove("set2", "B", "C", "D");
    }
}