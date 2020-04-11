/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.service;

import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
public interface SetOpsService {
    void add(String key, String... vals);

    Set<String> get(String key);

    Set<String> union(String key1, String key2);

    Set<String> difference(String key1, String key2);

    Set<String> intersect(String key1, String key2);

    void remove(String key, String... vals);
}
