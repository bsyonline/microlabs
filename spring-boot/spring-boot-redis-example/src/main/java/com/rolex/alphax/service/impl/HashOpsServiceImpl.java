/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.HashOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    public void hset(String key, Object hashKey, Object val) {
        redisTemplate.opsForHash().put(key, hashKey, val);
    }

    @Override
    public Object hget(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Long hdel(String key, Object hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

    @Override
    public Boolean hexists(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public Map hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Long hincrby(String key, Object hashKey, long val) {
        return redisTemplate.opsForHash().increment(key, hashKey, val);
    }

    @Override
    public Double hincrbyfloat(String key, Object hashKey, double val) {
        return redisTemplate.opsForHash().increment(key, hashKey, val);
    }

    @Override
    public Set hkeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Long hlen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public List hmget(String key, Collection hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    @Override
    public void hmset(String key, Map kvs) {
        redisTemplate.opsForHash().putAll(key, kvs);
    }

    @Override
    public Boolean hsetnx(String key, Object hashKey, Object val) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, val);
    }

    @Override
    public List hvals(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    @Override
    public Cursor<Map.Entry<Object, Object>> hscan(String key) {
        return redisTemplate.opsForHash().scan(key, ScanOptions.scanOptions().build());
    }

}
