/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;


/**
 * @author rolex
 * @since 2020
 */
public interface StringOpsService {
    void setString(String key, String val);

    String getString(String key);

    void setInt(String key, Integer val);

    Integer getInt(String key);

    void remove(String... key);

    /**
     * key对应的值是否等于val，不相等就set
     *
     * @param key
     * @param val
     * @param expire
     * @return
     */
    Boolean setIfPresent(String key, String val, long expire);

    /**
     * key是否存在，不存在就set
     *
     * @param key
     * @param val
     * @param expire
     * @return
     */
    Boolean setIfAbsent(String key, String val, long expire);

    Integer add(String key, Integer val);
}
