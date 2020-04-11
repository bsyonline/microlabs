/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.SetOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class SetOpsServiceImpl implements SetOpsService {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void add(String key, String... vals) {
        redisTemplate.opsForSet().add(key, vals);
    }

    @Override
    public Set<String> get(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Set<String> union(String key1, String key2) {
        return redisTemplate.opsForSet().union(key1, key2);
    }

    @Override
    public Set<String> difference(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1, key2);
    }

    @Override
    public Set<String> intersect(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    @Override
    public void remove(String key, String... vals) {
        redisTemplate.opsForSet().remove(key, vals);
    }
}
