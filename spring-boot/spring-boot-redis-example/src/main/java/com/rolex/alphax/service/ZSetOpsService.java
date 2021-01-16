/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
public interface ZSetOpsService {

    Boolean zadd(String key, String val, double score);

    Long zcard(String key);

    Long zcount(String key, double min, double max);

    Double zincrby(String key, String val, double score);

    Set<ZSetOperations.TypedTuple<String>> zrange(String key, long start, long end);

    Set<ZSetOperations.TypedTuple<String>> zrangebyscore(String key, double min, double max);

    Long zrank(String key, Object val);

    Long zrem(String key, String... values);

    Long zremrangebyrank(String key, long start, long end);

    Long zremrangebyscore(String key, double min, double max);

    Set<String> zrevrange(String key, long start, long end);

    Set<String> zrevrangebyscore(String key, double min, double max);

    Long zrevrank(String key, String val);

    Double zscore(String key, Object val);

    Long zunionstore(String key, String key1, String key2);

    Long zinterstore(String key, String key1, String key2);

    void zscan(String key);
}
