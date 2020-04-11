/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.ZSetOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class ZSetOpsServiceImpl implements ZSetOpsService {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void add(String key, String val, double d) {
        redisTemplate.opsForZSet().add(key, val, d);
    }

    @Override
    public void add(String key, Set<ZSetOperations.TypedTuple<String>> set) {
        redisTemplate.opsForZSet().add(key, set);
    }


    @Override
    public Set<String> range(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    @Override
    public Long remove(String key, String... vals) {
        return redisTemplate.opsForZSet().remove(key, vals);
    }

    @Override
    public Long rank(String key, String val) {
        return redisTemplate.opsForZSet().rank(key, val);
    }

    @Override
    public Double score(String key, String val) {
        return redisTemplate.opsForZSet().score(key, val);
    }

}
