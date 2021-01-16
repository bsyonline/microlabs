package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.HashOpsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HashOpsServiceImplTest {

    @Autowired
    HashOpsService hashOpsService;

    @Test
    public void test01Add() {
        hashOpsService.hset("user", "name", "tom");
        hashOpsService.hset("user", "age", "20");
        hashOpsService.hset("user", "gender", "male");
        System.out.println(hashOpsService.hget("user", "name"));
        Set keys = hashOpsService.hkeys("user");
        System.out.println(Arrays.toString(keys.toArray()));
        System.out.println(Arrays.toString(hashOpsService.hmget("user", keys).toArray()));
        hashOpsService.hdel("user", keys);
    }

    @Test
    public void cart() {
        String userId = "1001";
        String goodId = "18";
        String key = "cart:" + userId;
        // 添加商品
        Long incr = hashOpsService.hincrby(key, "18", 1L);
        Long incr1 = hashOpsService.hincrby(key, "19", 1L);
        Long incr2 = hashOpsService.hincrby(key, "20", 1L);
        System.out.println(incr);
        System.out.println(incr1);
        System.out.println(incr2);
        // 增加数量
        Long count1 = hashOpsService.hincrby(key, "18", 1L);
        System.out.println(count1);
        Long count = Long.parseLong((String)hashOpsService.hget(key, "18"));
        System.out.println("id 18 商品数量："+count);
        // 商品总数
        Long len = hashOpsService.hlen(key);
        System.out.println(len);
        // 删除商品
        // 所有商品总数
        Map<Object, Object> map = hashOpsService.hgetall(key);
        System.out.println(map);
    }

}