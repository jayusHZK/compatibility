package com.jayus.smallMyBatis.step13.session.defaults;

import com.alibaba.fastjson.JSON;
import com.jayus.smallMyBatis.step13.executor.Executor;
import com.jayus.smallMyBatis.step13.mapping.MappedStatement;
import com.jayus.smallMyBatis.step13.session.Configuration;
import com.jayus.smallMyBatis.step13.session.RowBounds;
import com.jayus.smallMyBatis.step13.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DefaultSqlSession
 * @Description: 默认 SqlSession 实现类
 * @date: 2024/10/16 16:37
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
        return this.selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.<T>selectList(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        logger.info("执行查询 statement：{} parameter：{}", statement, JSON.toJSONString(parameter));
        MappedStatement ms = configuration.getMappedStatment(statement);
        try {
            return executor.query(ms,parameter, RowBounds.DEFAULT,Executor.NO_RESULT_HANDLER,ms.getSqlSource().getBoundSql(parameter));
        } catch (SQLException e) {
            throw new RuntimeException("Error querying database.  Cause: " + e);
        }
    }

    @Override
    public int insert(String statement, Object parameter) {
        return update(statement,parameter);
    }

    @Override
    public int update(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatment(statement);
        try {
            return executor.update(ms,parameter);
        } catch (SQLException e) {
            throw new RuntimeException("Error updating database.  Cause: " + e);
        }
    }

    @Override
    public Object delete(String statement, Object parameter) {
        return update(statement,parameter);
    }

    @Override
    public void commit() {
        try {
            executor.commit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Error committing transaction.  Cause: " + e);
        }
    }

    @Override
    public Configuration getConfiguraction() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }
}
