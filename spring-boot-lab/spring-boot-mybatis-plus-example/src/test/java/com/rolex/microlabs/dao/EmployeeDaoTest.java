/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rolex.microlabs.employee.entity.Employee;
import com.rolex.microlabs.employee.mapper.EmployeeMapper;
import com.rolex.microlabs.employee.service.IEmployeeService;
import com.rolex.microlabs.employee.service.bo.EmployeeBo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.Service;
import java.util.ArrayList;
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
    IEmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;

    @Test
    public void insert() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setName("user" + i);
            employee.setAge(i);
            employee.setDeptId(1259510564693286913L);
            employee.setEmail("user" + i + "@gmail.com");
            list.add(employee);
        }
        employeeService.saveBatch(list);
    }

    @Test
    public void page1() {
        Page<Employee> page = new Page(3, 10);
        Page page1 = employeeService.page(page);
        System.out.println(page1.getRecords());
    }

    @Test
    public void page2() {
        Page<Employee> page = new Page(3, 10);
        Employee employee = new Employee();
        employee.setName("user");
        employee.setAge(30);
        Page<EmployeeBo> employeeBoPage = employeeMapper.customPage(page, employee);
        System.out.println(employeeBoPage.getRecords());
    }

}
