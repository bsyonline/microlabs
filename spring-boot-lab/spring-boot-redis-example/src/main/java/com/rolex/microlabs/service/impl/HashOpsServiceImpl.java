/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.HashOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class HashOpsServiceImpl implements HashOpsService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, String hashKey, String val) {
        redisTemplate.opsForHash().put(key, hashKey, val);
    }

    @Override
    public String get(String key, String hashKey) {
        return (String) redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public List multiGet(String key, Collection hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    @Override
    public Set keys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public void remove(String key, Collection hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys.toArray());
    }
}
