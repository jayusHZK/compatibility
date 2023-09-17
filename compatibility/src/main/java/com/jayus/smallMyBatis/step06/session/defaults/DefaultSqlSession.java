package com.jayus.smallMyBatis.step06.session.defaults;

import com.jayus.smallMyBatis.step06.executor.Executor;
import com.jayus.smallMyBatis.step06.mapping.MappedStatement;
import com.jayus.smallMyBatis.step06.session.Configuration;
import com.jayus.smallMyBatis.step06.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！，方法"+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object[] parameter) {
        try {
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            List<T> res = executor.query(mappedStatement, parameter, Executor.NO_RESULT_HANDLER, mappedStatement.getBoundSql());
            return res.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
