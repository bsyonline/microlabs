/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.rolex.alphax.util.YamlUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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
    private static final String DEFAULT_DATASOURCE_NAME = "ds1";
    private static final String DEFAULT_DATASOURCE_PREFIX = "spring.datasource.";

    @Bean(DEFAULT_DATASOURCE_NAME)
    @Primary
    @ConfigurationProperties(DEFAULT_DATASOURCE_PREFIX + DEFAULT_DATASOURCE_NAME)
    public DataSource ds1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public RoutingDataSource routingDataSource(@Qualifier(DEFAULT_DATASOURCE_NAME) DataSource ds1) {
        Map<Object, Object> targetDataSources = new HashMap<>(16);
        targetDataSources.put(DEFAULT_DATASOURCE_NAME, ds1);
        Map<String, Map<String, Object>> map = YamlUtil.lookupByPrefix(DEFAULT_DATASOURCE_PREFIX);
        if (map != null) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                if (!DEFAULT_DATASOURCE_NAME.equals(key)) {
                    Map<String, Object> dsMap = map.get(key);
                    String url = String.valueOf(dsMap.get("jdbc-url"));
                    String password = String.valueOf(dsMap.get("password"));
                    String driver = String.valueOf(dsMap.get("driver-class-name"));
                    String username = String.valueOf(dsMap.get("username"));
                    DataSource dataSource = DataSourceBuilder.create()
                            .url(url)
                            .username(username)
                            .password(password)
                            .driverClassName(driver)
                            .build();
                    targetDataSources.put(key, dataSource);
                }
            }
        }
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(ds1);
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("routingDataSource") DataSource dynamicDataSource)
            throws Exception {
        /*
          这里换成 MybatisSqlSessionFactoryBean ，否则使用 MybatisPlus报sql no binding异常
         */
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("routingDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
