/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author rolex
 * @since 2020
 */
@Configuration
@Setter
@Getter
@Slf4j
@ConfigurationProperties("spring.datasource")
public class DataSourceConfig {

    private Map<String, String> map = new HashMap<>();

    @Bean
    public RoutingDataSource routingDataSource() throws SQLException {
        Map<Object, Object> targetDataSources = new HashMap<>(16);
        Map<String, Map<String, String>> map = lookupDataSource();
        if (map != null) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                Map<String, String> dsMap = map.get(key);
                String url = dsMap.get("jdbc-url");
                String username = dsMap.get("username");
                String password = dsMap.get("password");
                String driver = dsMap.get("driver-class-name");
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource .setUrl(url);
                druidDataSource.setUsername(username);
                druidDataSource.setPassword(password);
                druidDataSource.setDriverClassName(driver);
                druidDataSource.init();
                targetDataSources.put(dsMap.get("name").toLowerCase(), druidDataSource);
            }
        }
        log.info("载入数据源{}个：{}", map.size(), map);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(targetDataSources.get("ds1"));
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("routingDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        return bean.getObject();

    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("routingDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    public Map<String, Map<String, String>> lookupDataSource() {
        Map<String, Map<String, String>> dsMap = new HashMap<>();
        for (Map.Entry<String, String> kv : map.entrySet()) {
            String key = kv.getKey().split("\\.")[0];
            if (dsMap.get(key) == null) {
                Map<String, String> dsMap1 = new HashMap<>();
                dsMap1.put(kv.getKey().split("\\.")[1], kv.getValue());
                dsMap.put(key, dsMap1);
            } else {
                dsMap.get(key).put(kv.getKey().split("\\.")[1], kv.getValue());
            }
        }
        return dsMap;
    }
}
