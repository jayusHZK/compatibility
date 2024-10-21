package com.jayus.smallMyBatis.step19.test.plugin;

import com.jayus.smallMyBatis.step19.executor.statement.StatementHandler;
import com.jayus.smallMyBatis.step19.plugin.Interceptor;
import com.jayus.smallMyBatis.step19.plugin.Intercepts;
import com.jayus.smallMyBatis.step19.plugin.Invocation;
import com.jayus.smallMyBatis.step19.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * @ClassName TestPlugin
 * @Description: 测试 interceptor
 * @date: 2024/10/20 22:41
 */
@Intercepts(@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class}))
public class TestPlugin implements Interceptor {

    Logger logger = LoggerFactory.getLogger(TestPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler) invocation.getTarget();
        logger.info("执行 SQL::"+handler.getBoundSql().getSql());
        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println(properties.getProperty("test00"));
    }
}
