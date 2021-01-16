/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.ListOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class ListOpsServiceImpl implements ListOpsService {
    @Autowired
    StringRedisTemplate redisTemplate;


    @Override
    public String blpop(String key, long timeout) {
        return redisTemplate.opsForList().leftPop(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String brpop(String key, long timeout) {
        return redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String brpoplpush(String rkey, String lkey, long timeout) {
        return redisTemplate.opsForList().rightPopAndLeftPush(rkey, lkey, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String lindex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public Long linsert(String key, String val) {
        return redisTemplate.opsForList().leftPush(key, val);
    }

    @Override
    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public String lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Long lpush(String key, String val) {
        return redisTemplate.opsForList().leftPush(key, val);
    }

    @Override
    public Long lpush(String key, Collection<String> values) {
        return redisTemplate.opsForList().leftPushAll(key, values);
    }

    @Override
    public Long lpushx(String key, String val) {
        return redisTemplate.opsForList().leftPushIfPresent(key, val);
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    public Long lrem(String key, long count, String val) {
        return redisTemplate.opsForList().remove(key, count, val);
    }

    @Override
    public void lset(String key, long index, String val) {
        redisTemplate.opsForList().set(key, index, val);
    }

    @Override
    public void ltrim(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    @Override
    public String rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public String rpoplpush(String rkey, String lkey) {
        return redisTemplate.opsForList().rightPopAndLeftPush(rkey, lkey);
    }

    @Override
    public Long rpush(String key, String val) {
        return redisTemplate.opsForList().rightPush(key, val);
    }

    @Override
    public Long rpush(String key, Collection<String> values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @Override
    public Long rpushx(String key, String val) {
        return redisTemplate.opsForList().rightPushIfPresent(key, val);
    }
}
