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

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringOpsServiceImplTest {
    @Autowired
    private StringOpsService stringOpsService;

    @Before
    public void init() {

    }

    @Test
    public void test01SetString() {
        stringOpsService.setString("hello", "world");
        String val = stringOpsService.getString("hello");
        TestCase.assertEquals("world", val);
        TestCase.assertEquals(new Boolean(false), stringOpsService.setIfAbsent("hello", "redis", 10));
        TestCase.assertEquals(new Boolean(true), stringOpsService.setIfAbsent("redis", "hello", 10));
        TestCase.assertEquals(new Boolean(true), stringOpsService.setIfPresent("redis", "hello2", 10));
        stringOpsService.remove("hello", "redis");
    }

    @Test
    public void test02SetInt() {
        stringOpsService.setInt("num", 1);
        Integer val = stringOpsService.getInt("num");
        TestCase.assertEquals(new Integer(1), val);
        TestCase.assertEquals(new Integer(10), stringOpsService.add("num", 9));
        stringOpsService.remove("num");
    }
}