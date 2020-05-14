/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rolex
 * @since 2020
 */
public class DataSourceContextHolder {
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static void set(String dbType) {
        contextHolder.set(dbType);
        map.put(dbType, dbType);
    }

    public static String get() {
        return contextHolder.get();
    }
}
