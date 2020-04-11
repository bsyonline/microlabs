/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.ListOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class ListOpsServiceImpl implements ListOpsService {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void add(String key, long index, String val) {
        redisTemplate.opsForList().set(key, index, val);
    }

    @Override
    public Long lpush(String key, String val) {
        return redisTemplate.opsForList().leftPush(key, val);
    }

    @Override
    public Long rpush(String key, String val) {
        return redisTemplate.opsForList().rightPush(key, val);
    }

    @Override
    public String get(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long remove(String key, long index, String val) {
        return redisTemplate.opsForList().remove(key, index, val);
    }

    @Override
    public String lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public String rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public List<String> list(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }
}
