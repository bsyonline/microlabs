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
public interface ListOpsService {

    void add(String key, long index, String val);

    Long lpush(String key, String val);

    Long rpush(String key, String val);

    String get(String key, long index);

    Long remove(String key, long index, String val);

    String lpop(String key);

    String rpop(String key);

    List<String> list(String key, long start, long end);

    long size(String key);
}
