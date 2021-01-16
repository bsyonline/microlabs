/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service.impl;

import com.rolex.alphax.service.SetOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Long sadd(String key, String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Set<String> sdiff(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1, key2);
    }

    @Override
    public Long sdiffstore(String key, String key1, String key2) {
        return redisTemplate.opsForSet().differenceAndStore(key, key1, key2);
    }

    @Override
    public Set<String> sinter(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    @Override
    public Long sinterstore(String key, String key1, String key2) {
        return redisTemplate.opsForSet().intersectAndStore(key, key1, key2);
    }

    @Override
    public Boolean sismember(String key, Object val) {
        return redisTemplate.opsForSet().isMember(key, val);
    }

    @Override
    public Set<String> smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Boolean smove(String key, String val, String key1) {
        return redisTemplate.opsForSet().move(key, val, key1);
    }

    @Override
    public String spop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    @Override
    public String srandmember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    @Override
    public List<String> srandmember(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    @Override
    public Long srem(String key, String... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    @Override
    public Set<String> sunion(String key1, String key2) {
        return redisTemplate.opsForSet().union(key1, key2);
    }

    @Override
    public Long sunionstore(String key, String key1, String key2) {
        return redisTemplate.opsForSet().unionAndStore(key, key1, key2);
    }

    @Override
    public void sscan(String key) {

    }
}
