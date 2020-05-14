/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        log.info("===============routingDataSource");
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("===============LookupKey");
        return dataSourceMap.get(DataSourceContextHolder.get());
    }

    public static void setDataSource(String type, Object dataSource) {
        RoutingDataSource.dataSourceMap.put(type, dataSource);
    }
}
