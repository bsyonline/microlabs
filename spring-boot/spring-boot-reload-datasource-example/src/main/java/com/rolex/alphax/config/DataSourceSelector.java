/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.alphax.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rolex
 * @since 2020
 */
@Aspect
@Component
@Slf4j
public class DataSourceSelector {
    @Before("execution(* com.rolex.alphax.controller.*.*(..))")
    public void datasourceSwitch(JoinPoint jp) {
        Map<String, Object> argsMap = getArgsMap(jp);
        if (argsMap != null) {
            String val = (String) argsMap.get("type");
            DataSourceContextHolder.set(val);
        }
        log.info("{}", DataSourceContextHolder.get());
    }

    /**
     * 获取参数Map集合
     *
     * @param joinPoint
     * @return
     */
    Map<String, Object> getArgsMap(JoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>(16);

        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }

        return param;
    }
}
