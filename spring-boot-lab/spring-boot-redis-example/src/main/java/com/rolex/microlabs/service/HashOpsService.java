/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;


import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
public interface HashOpsService {


    void set(String key, String hashKey, String val);

    String get(String key, String hashKey);

    List multiGet(String key, Collection hashKeys);

    Set keys(String key);

    void remove(String key, Collection hashKeys);
}
