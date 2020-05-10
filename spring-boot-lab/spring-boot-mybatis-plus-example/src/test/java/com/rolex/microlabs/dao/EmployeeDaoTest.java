/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.rolex.microlabs.model.Employee;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;
import java.lang.annotation.ElementType;
import java.util.Arrays;
import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDaoTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void insert() {
        Arrays.asList("Adele", "Taylor", "John", "Paul", "Bill", "Tim", "Page", "Jobs", "Jeff")
                .forEach(e -> {
            Employee employee = new Employee();
            employee.setName(e);
            employee.setAge(31);
            employee.setEmail(e.toLowerCase()+"@gmail.com");
            employee.setDeptId(1259510564693286913L);
            int insert = employeeMapper.insert(employee);
        });
    }

    @Test
    public void queryAll(){
        List<Employee> employees = employeeMapper.selectList(null);
        employees.stream().forEach(e-> System.out.println(e));
        TestCase.assertEquals(9, employees.size());
    }

    @Test
    public void query1(){
        List<Employee> employees = employeeMapper.selectList(null);
        employees.stream().forEach(e-> System.out.println(e));
        TestCase.assertEquals(9, employees.size());
    }
}
