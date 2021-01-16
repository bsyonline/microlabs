/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service;


import org.springframework.data.redis.connection.BitFieldSubCommands;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author rolex
 * @since 2020
 */
public interface StringOpsService {
    Integer append(String key, String val);

    Long bitcount(String key);

    List<Long> bitop(String key, BitFieldSubCommands subCommands);

    Long decr(String key);

    Long decrby(String key, long value);

    String get(Object key);

    Boolean getbit(String key, long offset);

    String getrange(String key, long start, long end);

    String getset(String key, String value);

    Long incr(String key);

    Long incrby(String key, long val);

    Double incrbyfloat(String key, double val);

    List<String> mget(Collection keys);

    void mset(Map<String, String> kv);

    Boolean msetnx(Map<String, String> kv);

    void psetex(String key, String value, long expire);

    void set(String key, String value);

    void set(String key, String value, long expire);

    void set(String key, String value, long expire, TimeUnit timeUnit);

    void set(String key, String value, long expire, TimeUnit timeUnit, SetOp op);

    void set(String key, String value, SetOp op);

    Boolean setbit(String key, long offset, boolean value);

    void setex(String key, String value, long expire);

    Boolean setnx(String key, String value);

    Boolean setnx(String key, String value, long expire);

    void setrange();

    Long strlen(String key);
}
