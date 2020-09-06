/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.microlabs.config;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author rolex
 * @since 2019
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<DataSourceTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DataSourceTypeEnum datasourceType) {
        contextHolder.set(datasourceType);
    }

    public static void remove() {
        contextHolder.remove();
    }

    public static DataSourceTypeEnum get() {
        DataSourceTypeEnum datasourceType = contextHolder.get();
        if (datasourceType == null)
            datasourceType = DataSourceTypeEnum.MASTER;
        System.out.println("所使用的数据源为：" + datasourceType);
        return datasourceType;
    }


}