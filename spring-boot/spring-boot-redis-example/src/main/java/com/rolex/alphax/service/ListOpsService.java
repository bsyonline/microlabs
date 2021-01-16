/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.service;

import java.util.Collection;
import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
public interface ListOpsService {

    String blpop(String key, long timeout);

    String brpop(String key, long timeout);

    String brpoplpush(String rkey, String lkey, long timeout);

    String lindex(String key, long index);

    Long linsert(String key, String val);

    Long llen(String key);

    String lpop(String key);

    Long lpush(String key, String val);

    Long lpush(String key, Collection<String> values);

    Long lpushx(String key, String val);

    List<String> lrange(String key, long start, long end);

    Long lrem(String key, long count, String val);

    void lset(String key, long index, String val);

    void ltrim(String key, long start, long end);

    String rpop(String key);

    String rpoplpush(String rkey, String lkey);

    Long rpush(String key, String val);

    Long rpush(String key, Collection<String> values);

    Long rpushx(String key, String val);
}
