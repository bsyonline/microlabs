/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        log.info("===============routingDataSource");
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("===============LookupKey");
        return DataSourceContextHolder.get();
    }

}
