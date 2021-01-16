/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.config;

/**
 * @author rolex
 * @since 2020
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void set(DataSourceType dbType) {
        contextHolder.set(dbType);
    }

    public static DataSourceType get() {
        return contextHolder.get();
    }
}
