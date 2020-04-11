/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
public interface ZSetOpsService {

    void add(String key, String val, double d);

    void add(String key, Set<ZSetOperations.TypedTuple<String>> set);

    Set<String> range(String key, long start, long end);

    Long rank(String key, String val);

    Double score(String key1, String key2);

    Long remove(String key, String... vals);
}
