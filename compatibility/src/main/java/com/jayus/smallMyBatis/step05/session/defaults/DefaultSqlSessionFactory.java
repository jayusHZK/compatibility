package com.jayus.smallMyBatis.step05.session.defaults;

import com.jayus.smallMyBatis.step05.session.Configuration;
import com.jayus.smallMyBatis.step05.session.SqlSession;
import com.jayus.smallMyBatis.step05.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
