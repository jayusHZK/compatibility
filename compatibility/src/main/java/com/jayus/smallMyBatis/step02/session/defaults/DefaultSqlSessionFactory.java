package com.jayus.smallMyBatis.step02.session.defaults;

import com.jayus.smallMyBatis.step02.binding.MapperRegistry;
import com.jayus.smallMyBatis.step02.session.SqlSession;
import com.jayus.smallMyBatis.step02.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
