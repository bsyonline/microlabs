package com.rolex.microlabs.service;

import com.rolex.microlabs.SpringBootMybatisMultiDatasourceExampleApplication;
import com.rolex.microlabs.dao1.UserDao;
import com.rolex.microlabs.dao2.OrderDao;
import com.rolex.microlabs.model.Order;
import com.rolex.microlabs.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={SpringBootMybatisMultiDatasourceExampleApplication.class})
public class SpringBootMybatisMultiDatasourceExampleApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    OrderDao orderDao;

    @Test
    public void contextLoads() {
        Order order = orderDao.findByOrderNo(1);
        Integer userId = order.getUserId();
        User user = userDao.findById(userId);
        System.out.println(order);
        System.out.println(user);
    }

}
