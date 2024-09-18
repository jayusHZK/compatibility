package com.jayus.smallMyBatis.step11.session.defaults;

import com.alibaba.fastjson.JSON;
import com.jayus.smallMyBatis.step11.executor.Executor;
import com.jayus.smallMyBatis.step11.mapping.MappedStatement;
import com.jayus.smallMyBatis.step11.session.Configuration;
import com.jayus.smallMyBatis.step11.session.RowBounds;
import com.jayus.smallMyBatis.step11.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName DefaultSqlSession
 * @Description: 默认 SqlSession 实现类
 * @date: 2024/5/13 17:59
 */
public class DefaultSqlSession implements SqlSession {

    private Logger logger = LoggerFactory.getLogger(DefaultSqlSession.class);

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return this.selectOne(statement,null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        logger.info("执行查询 statement：{} parameter：{}", statement, JSON.toJSONString(parameter));
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER
                , ms.getSqlSource().getBoundSql(parameter));
        return list.get(0);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }
}
