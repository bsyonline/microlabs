/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;


import org.springframework.data.redis.core.Cursor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
public interface HashOpsService {

    void hset(String key, Object hashKey, Object val);

    Object hget(String key, Object hashKey);

    Long hdel(String key, Object hashKey);

    Boolean hexists(String key, Object hashKey);

    Map hgetall(String key);

    Long hincrby(String key, Object hashKey, long val);

    Double hincrbyfloat(String key, Object hashKey, double val);

    Set hkeys(String key);

    Long hlen(String key);

    List hmget(String key, Collection hashKeys);

    void hmset(String key, Map kvs);

    Boolean hsetnx(String key, Object hashKey, Object val);

    List hvals(String key);

    Cursor<Map.Entry<Object, Object>> hscan(String key);
}
