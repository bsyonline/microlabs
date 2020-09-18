/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author rolex
 * @since 2020
 */
public class NacosSDK {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "localhost:8848";
        String dataId = "application.properties";
        String group = "spring-cloud-alibaba-nacos";
        String tenant = "e400be7d-039f-460a-bbb9-d2a2752fcb9e";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, tenant);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println("配置为：" + content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("监听器-配置更新了：" + configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });

        boolean isPublishOk = configService.publishConfig(dataId, group, "application.name=nacos");
        System.out.println("发布了配置：" + isPublishOk);

        Thread.sleep(3000);
        content = configService.getConfig(dataId, group, 5000);
        System.out.println("配置为：" + content);

        Thread.sleep(3000);
        boolean isPublishOk1 = configService.publishConfig(dataId, group, "application.name=nacos1");
        System.out.println("更新了配置：" + isPublishOk1);

        Thread.sleep(3000);
        content = configService.getConfig(dataId, group, 5000);
        System.out.println("更新后配置为：" + content);

        Thread.sleep(3000);
        boolean isRemoveOk = configService.removeConfig(dataId, group);
        System.out.println("删除了配置：" + isRemoveOk);

        Thread.sleep(3000);
        content = configService.getConfig(dataId, group, 5000);
        System.out.println("删除后配置为：" + content);
        Thread.sleep(300000);
    }
}
