package com.jayus.smallMyBatis.step03.session.defaults;

import com.jayus.smallMyBatis.step03.session.Configuration;
import com.jayus.smallMyBatis.step03.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！方法："+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T)("你被代理了！,方法:" + statement + ",入参:" + parameter);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type,this);
    }
}
