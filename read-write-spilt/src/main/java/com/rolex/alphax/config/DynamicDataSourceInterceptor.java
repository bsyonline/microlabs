/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rolex
 * @since 2019
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    private static final String REGEX = ".*insert.*|.*delete.*|.*update.*";

    private static final ConcurrentHashMap<String, DataSourceTypeEnum> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement) objects[0];
            DataSourceTypeEnum dynamicDataSourceType = null;
            if ((dynamicDataSourceType = cacheMap.get(mappedStatement.getId())) == null) {
                // read
                if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    // 查询自增主键需要查主库
                    if (mappedStatement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                        dynamicDataSourceType = DataSourceTypeEnum.MASTER;
                    } else {
                        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
                        String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                        if (sql.matches(REGEX)) {
                            dynamicDataSourceType = DataSourceTypeEnum.MASTER;
                        } else {
                            dynamicDataSourceType = DataSourceTypeEnum.SLAVE;
                        }
                    }
                } else {
                    dynamicDataSourceType = DataSourceTypeEnum.MASTER;
                }
                System.out.println("设置方法:" + mappedStatement.getId() + "; use:" + dynamicDataSourceType + "; Strategy,SqlCommandType:" + mappedStatement.getSqlCommandType().name());
                cacheMap.put(mappedStatement.getId(), dynamicDataSourceType);
            }
            DynamicDataSourceHolder.set(dynamicDataSourceType);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        if (o instanceof Executor)
            return Plugin.wrap(o, this);
        else
            return o;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
