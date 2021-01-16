/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.config;

/**
 * @author rolex
 * @since 2020
 */
public class DataSourceContextHolder {
    public static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void set(String dbType) {
        CONTEXT_HOLDER.set(dbType);
    }

    public static String get() {
        return CONTEXT_HOLDER.get();
    }
}
