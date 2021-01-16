/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.SetOp;
import com.rolex.alphax.service.StringOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    public Integer append(String key, String val) {
        return redisTemplate.opsForValue().append(key, val);
    }

    @Override
    public Long bitcount(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    @Override
    public List<Long> bitop(String key, BitFieldSubCommands subCommands) {
        return redisTemplate.opsForValue().bitField(key, subCommands);
    }

    @Override
    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    @Override
    public Long decrby(String key, long val) {
        return redisTemplate.opsForValue().decrement(key, val);
    }

    @Override
    public String get(Object key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean getbit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    @Override
    public String getrange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    @Override
    public String getset(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    @Override
    public Long incrby(String key, long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    @Override
    public Double incrbyfloat(String key, double value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    @Override
    public List<String> mget(Collection keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void mset(Map<String, String> kv) {
        redisTemplate.opsForValue().multiSet(kv);
    }

    @Override
    public Boolean msetnx(Map<String, String> kv) {
        return redisTemplate.opsForValue().multiSetIfAbsent(kv);
    }

    @Override
    public void psetex(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void set(String key, String value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    @Override
    public void set(String key, String value, long expire, TimeUnit timeUnit, SetOp op) {
        switch (op) {
            case NE:
                redisTemplate.opsForValue().setIfAbsent(key, value, expire, timeUnit);
                break;
            case XX:
                redisTemplate.opsForValue().setIfPresent(key, value, expire, timeUnit);
                break;
        }
    }

    @Override
    public void set(String key, String value, SetOp op) {
        switch (op) {
            case NE:
                redisTemplate.opsForValue().setIfAbsent(key, value);
                break;
            case XX:
                redisTemplate.opsForValue().setIfPresent(key, value);
                break;
        }
    }

    @Override
    public Boolean setbit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    @Override
    public void setex(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public Boolean setnx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public Boolean setnx(String key, String value, long expire) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public void setrange() {
    }

    @Override
    public Long strlen(String key) {
        return redisTemplate.opsForValue().size(key);
    }

}
