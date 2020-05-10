/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;

import java.util.List;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
public interface SetOpsService {

    Long sadd(String key, String... values);

    Long scard(String key);

    Set<String> sdiff(String key1, String key2);

    Long sdiffstore(String key, String key1, String key2);

    Set<String> sinter(String key1, String key2);

    Long sinterstore(String key, String key1, String key2);

    Boolean sismember(String key, Object val);

    Set<String> smembers(String key);

    Boolean smove(String key, String val, String key1);

    String spop(String key);

    String srandmember(String key);

    List<String> srandmember(String key, long count);

    Long srem(String key, String... values);

    Set<String> sunion(String key1, String key2);

    Long sunionstore(String key, String key1, String key2);

    void sscan(String key);
}
