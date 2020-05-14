/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.config;

import com.rolex.microlabs.util.YamlUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@Component
public class DataSourceLoader {

    @Bean("defaultDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.default")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public RoutingDataSource routingDataSource(@Qualifier("defaultDataSource") DataSource defaultDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("default", defaultDataSource);
        Map<String, Map<String, Object>> map = YamlUtil.lookupByPrefix("spring.datasource.");
        if (map != null) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                if (!"default".equals(key)) {
                    Map<String, Object> dsMap = map.get(key);
                    String url = String.valueOf(dsMap.get("jdbc-url"));
                    String password = String.valueOf(dsMap.get("password"));
                    String driver = String.valueOf(dsMap.get("driver-class-name"));
                    String username = String.valueOf(dsMap.get("username"));
                    DataSource dataSource = DataSourceBuilder.create().url(url).username(username).password(password).driverClassName(driver).build();
                    targetDataSources.put(key, dataSource);
                }
            }
        }
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(defaultDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }

}
