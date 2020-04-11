/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service.impl;

import com.rolex.microlabs.service.HyperLogLogOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class HyperLogLogOpsServiceImpl implements HyperLogLogOpsService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void add(String key, String val){
        redisTemplate.opsForHyperLogLog().add(key, val);
    }

    @Override
    public void merge(String key, String... keys){
        redisTemplate.opsForHyperLogLog().union(key, keys);
    }

    @Override
    public Long count(String key){
        return redisTemplate.opsForHyperLogLog().size(key);
    }

}
