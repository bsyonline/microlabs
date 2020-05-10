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
    public Boolean zadd(String key, String val, double score) {
        return redisTemplate.opsForZSet().add(key, val, score);
    }

    @Override
    public Long zcard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }

    @Override
    public Double zincrby(String key, String val, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, val, score);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> zrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<String>> zrangebyscore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    @Override
    public Long zrank(String key, Object val) {
        return redisTemplate.opsForZSet().rank(key, val);
    }

    @Override
    public Long zrem(String key, String... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    @Override
    public Long zremrangebyrank(String key, long start, long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }

    @Override
    public Long zremrangebyscore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    @Override
    public Set<String> zrevrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    @Override
    public Set<String> zrevrangebyscore(String key, double min, double max) {
        return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
    }

    @Override
    public Long zrevrank(String key, String val) {
        return redisTemplate.opsForZSet().reverseRank(key, val);
    }

    @Override
    public Double zscore(String key, Object val) {
        return redisTemplate.opsForZSet().score(key, val);
    }

    @Override
    public Long zunionstore(String key, String key1, String key2) {
        return redisTemplate.opsForZSet().unionAndStore(key, key1, key2);
    }

    @Override
    public Long zinterstore(String key, String key1, String key2) {
        return redisTemplate.opsForZSet().intersectAndStore(key, key1, key2);
    }

    @Override
    public void zscan(String key) {

    }
}
