/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.microlabs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2019
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Autowired
    DataSource readDataSource;
    @Autowired
    DataSource writeDataSource;

    @Override
    public void afterPropertiesSet() {
        if (writeDataSource == null) {
            throw new IllegalArgumentException("write data source must not be null");
        }
        setDefaultTargetDataSource(writeDataSource);
        Map target = new HashMap();
        target.put(DataSourceTypeEnum.MASTER.name(), writeDataSource);
        if (readDataSource != null) {
            target.put(DataSourceTypeEnum.SLAVE.name(), readDataSource);
        }
        setTargetDataSources(target);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceTypeEnum dataSourceType = DynamicDataSourceHolder.get();
        if (dataSourceType == null || dataSourceType == DataSourceTypeEnum.MASTER) {
            return DataSourceTypeEnum.MASTER.name();
        }
        return DataSourceTypeEnum.SLAVE.name();
    }
}
