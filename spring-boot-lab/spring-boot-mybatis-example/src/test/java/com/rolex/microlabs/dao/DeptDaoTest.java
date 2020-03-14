package com.rolex.microlabs.dao;

import com.rolex.microlabs.model.Department;
import com.rolex.microlabs.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptDaoTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void findAll() {
        List<Department> departments = departmentDao.findAll();
        System.out.println(departments);
    }

}
