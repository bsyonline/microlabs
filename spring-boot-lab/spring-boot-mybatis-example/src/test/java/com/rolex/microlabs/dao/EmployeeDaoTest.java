package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.Employee;
import com.rolex.microlabs.model.Gender;
import com.rolex.microlabs.model.Skill;
import com.rolex.microlabs.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void findAll() {
        List<Employee> employees = employeeDao.findAll();
        System.out.println(employees);
    }

}
