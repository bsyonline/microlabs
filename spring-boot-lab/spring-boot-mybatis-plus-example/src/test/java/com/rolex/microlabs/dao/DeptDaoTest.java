/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.Dept;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DeptDaoTest {

    @Autowired
    DeptMapper deptMapper;

    @Test
    public void insert() {
        AtomicInteger count = new AtomicInteger();
        Arrays.asList("Sales","R&D","Market","QA").forEach(d->{
            Dept dept = new Dept();
            dept.setName(d);
            int insert = deptMapper.insert(dept);
            count.addAndGet(insert);
        });

        TestCase.assertEquals(4, count.get());
    }


}
