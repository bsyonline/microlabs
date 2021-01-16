/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax;

import com.rolex.alphax.dao.UserMapper;
import com.rolex.alphax.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author rolex
 * @since 2019
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User(1, "Tom", 19);
        userMapper.insert(user);
    }

    @Test
    public void update(){
        User user = new User(1, "Tom", 20);
        userMapper.update(user);
    }

    @Test
    public void delete(){
        User user = new User(1, "Tom", 19);
        userMapper.delete(user);
    }

    @Test
    public void findOne(){
        User user = userMapper.findOne(1);
        System.out.println(user);
    }

    @Test
    public void findAll(){
        List<User> list = userMapper.findAll();
        System.out.println(list);
    }
}
