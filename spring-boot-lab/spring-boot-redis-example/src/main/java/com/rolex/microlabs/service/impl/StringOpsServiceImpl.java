/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.StringOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class StringOpsServiceImpl implements StringOpsService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void setString(String key, String val) {
        redisTemplate.opsForValue().set(key, val);
    }

    @Override
    public String getString(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String... keys) {
        redisTemplate.delete(Arrays.asList(keys));
    }

    @Override
    public void setInt(String key, Integer val) {
        redisTemplate.opsForValue().set(key, String.valueOf(val));
    }

    @Override
    public Integer getInt(String key) {
        return Integer.parseInt(redisTemplate.opsForValue().get(key));
    }

    @Override
    public Boolean setIfAbsent(String key, String val, long expire) {
        return redisTemplate.opsForValue().setIfAbsent(key, val, expire, TimeUnit.SECONDS);
    }

    @Override
    public Boolean setIfPresent(String key, String val, long expire) {
        return redisTemplate.opsForValue().setIfPresent(key, val, expire, TimeUnit.SECONDS);
    }

    @Override
    public Integer add(String key, Integer val){
        return redisTemplate.opsForValue().increment(key, val).intValue();
    }
}
