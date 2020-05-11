/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
@Configuration
public class DataSourceConfig {

    @Bean("dataSource1")
    @Primary
    @ConfigurationProperties("spring.datasource.ds1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dataSource2")
    @ConfigurationProperties("spring.datasource.ds2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dataSource3")
    @ConfigurationProperties("spring.datasource.ds3")
    public DataSource dataSource3() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public RoutingDataSource routingDataSource(@Qualifier("dataSource1") DataSource dataSource1,
                                               @Qualifier("dataSource2") DataSource dataSource2,
                                               @Qualifier("dataSource3") DataSource dataSource3) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.DS1, dataSource1);
        targetDataSources.put(DataSourceType.DS2, dataSource2);
        targetDataSources.put(DataSourceType.DS3, dataSource3);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(dataSource1);
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

}
