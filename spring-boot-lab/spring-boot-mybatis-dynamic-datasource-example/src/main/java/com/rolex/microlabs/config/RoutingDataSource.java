/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static RoutingDataSource instance;
    private static byte[] lock = new byte[0];
    private static Map<Object, Object> dataSourceMap = new HashMap<>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        log.info("===============routingDataSource");
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
        log.info("===============LookupKey");
        return DataSourceContextHolder.get().name().toLowerCase();
    }

    @Override
    protected javax.sql.DataSource determineTargetDataSource() {
        Object lookupKey = determineCurrentLookupKey();
        DataSource dataSource = (DataSource) dataSourceMap.get(lookupKey);
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        log.info("{}", druidDataSource.getUrl());
        return dataSource;
    }
}
