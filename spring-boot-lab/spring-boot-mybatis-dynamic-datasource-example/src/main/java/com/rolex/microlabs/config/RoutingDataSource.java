/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static RoutingDataSource instance;
    private static byte[] lock = new byte[0];
    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();// 必须添加该句，否则新添加数据源无法识别到
    }

    /*public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    public static synchronized RoutingDataSource getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new RoutingDataSource();
                }
            }
        }
        return instance;
    }*/

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
