package com.lesson.source.mybatis.quickstart.plugins;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class SelectInterceptor implements Interceptor {


    private static final Logger log = LoggerFactory.getLogger(SelectInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        log.info("intercept target class:{}",invocation.getTarget().getClass().getInterfaces());
        log.info("intercept target method:{}",invocation.getMethod().getName());
        log.info("intercept args:{}",invocation.getArgs().length);

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(invocation.getArgs()[1]);
        log.info(String.format("plugin output sql = %s , param=%s", boundSql.getSql(),boundSql.getParameterObject()));

        Object result = invocation.proceed();
        log.info("execute over");
        return result;
    }

    @Override
    public Object plugin(Object target) {
        log.info("plugin:{}",target.getClass());
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

        log.info("properties:{}", JSON.toJSONString(properties));
    }
}
